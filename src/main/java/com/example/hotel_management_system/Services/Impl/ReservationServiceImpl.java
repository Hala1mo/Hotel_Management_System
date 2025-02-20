package com.example.hotel_management_system.Services.Impl;

import com.example.hotel_management_system.DTO.Reservation.ReservationDTO;
import com.example.hotel_management_system.DTO.Reservation.Reserve_Add_OnDTO;
import com.example.hotel_management_system.DTO.Reservation.Reserve_RoomDTO;
import com.example.hotel_management_system.DTO.Room.RoomDTO;
import com.example.hotel_management_system.Mapper.*;
import com.example.hotel_management_system.Models.*;
import com.example.hotel_management_system.Models.Enum.paymentMethod;
import com.example.hotel_management_system.Models.Enum.reservationStatus;
import com.example.hotel_management_system.Models.Enum.roomStatus;
import com.example.hotel_management_system.Repository.*;
import com.example.hotel_management_system.Services.ReservationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    ReservationRepository reservationRepository;
    Reservation_RoomRepository reservationRoomRepository;
    UserRepository userRepository;
    RoomRepository roomRepository;
    InvoiceRepository invoiceRepository;

    Add_OnRepository addOnRepository;
    Reserve_Add_OnRepository reserve_Add_OnRepository;

    private TaskScheduler taskScheduler;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository, UserRepository userRepository, RoomRepository roomRepository,Reservation_RoomRepository reservationRoomRepository, InvoiceRepository invoiceRepository,Add_OnRepository addOnRepository, Reserve_Add_OnRepository reserve_Add_OnRepository,TaskScheduler taskScheduler) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.reservationRoomRepository=reservationRoomRepository;
        this.invoiceRepository= invoiceRepository;
        this.reserve_Add_OnRepository= reserve_Add_OnRepository;
        this.addOnRepository= addOnRepository;
        this.taskScheduler=taskScheduler;

    }


    @Transactional
    public ResponseEntity<?> reserveBooking(ReservationDTO request) {
        User userId = userRepository.findAllById(request.getUser_id());
        double totalPrice = 0;
        reservationStatus status = reservationStatus.Pending;
        List<Reserve_RoomDTO> reservedRooms = request.getBooking_room();

        if (checkNumberOfGuests(reservedRooms, request) == 0) {
            throw new IllegalArgumentException("Room capacity exceeded");
        }

        paymentMethod payment = request.getPaymentMethod();
        if (payment == paymentMethod.PAY_LATER) {
            status = reservationStatus.Pending;
        } else if (payment == paymentMethod.PAY_NOW) {
            status = reservationStatus.Confirmed;
        } else {
            throw new IllegalArgumentException("Payment method is required");
        }

        for (Reserve_RoomDTO reserveRoomDTO : reservedRooms) {
            Room room = roomRepository.findAllById(reserveRoomDTO.getRoom_id());
            if (!isRoomAvailable(room, request.getCheckInDate(), request.getCheckOutDate())) {
                throw new IllegalArgumentException("Room " + room.getId() + " is not available for the requested period");
            }
        }
       // updateRoomStatusForReservations();
        Reservation reservation = Reservation.builder()
                .user(userId)
                .status(status)
                .num_children(request.getNum_children())
                .num_adults(request.getNum_adults())
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .payment_Method(payment)
                .booking_room(new ArrayList<>())
                .add_on(new ArrayList<>())
                .build();

        reservationRepository.save(reservation);

        List<Reserve_Add_OnDTO> additionList = request.getAdditions();
        for (Reserve_RoomDTO reserveRoomDTO : reservedRooms) {
            Room room = roomRepository.findAllById(reserveRoomDTO.getRoom_id());
            Reserve_Room reserve_room = Reserve_RoomMapper.toEntity(room, reservation);
            reservation.getBooking_room().add(reserve_room);

            room.getBooking_room().add(reserve_room);
            reservationRoomRepository.save(reserve_room);
            totalPrice = totalPrice + room.getRoomType().getPrice();
            roomRepository.save(room);
        }
        long diffInMillies = Math.abs(reservation.getCheckOutDate().getTime() - reservation.getCheckInDate().getTime());
        int numberOfStay = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        for(int i=0;i< additionList.size();++i){
            Add_On addition=addOnRepository.findAllById(additionList.get(i).getAdd_on_id());
            Reserve_Add_On reserve_add_on= Reserve_Add_OnMapper.toEntity(reservation,addition);
            reservation.getAdd_on().add(reserve_add_on);
            addition.getAdd_on_id().add(reserve_add_on);
            totalPrice=totalPrice+(numberOfStay*addition.getPrice());
            reserve_Add_OnRepository.save(reserve_add_on);
            addOnRepository.save(addition);
        }

        Invoice invoice = InvoiceMapper.toEntity(reservation, totalPrice);
        invoiceRepository.save(invoice);
        updateRoomStatusForReservations();
        return ResponseEntity.ok(InvoiceMapper.mapToInvoiceDetailsDTO(invoice));
    }
    private boolean isRoomAvailable(Room room, Date checkInDate, Date checkOutDate) {
        List<Reservation> overlappingReservations = reservationRepository.findOverlappingReservations(room.getId(), checkInDate, checkOutDate);

        return overlappingReservations.isEmpty();
    }


    public void updateRoomStatusForReservations() {

        List<Reserve_Room> reserve_room= reservationRoomRepository.findAll();

        for (Reserve_Room reserve_roomm : reserve_room) {

            Date checkInDate = reserve_roomm.getBooking().getCheckInDate();
            System.out.println("halaaa");
            System.out.println(reserve_roomm .getRoom_id().getId());
            System.out.print(checkInDate);
            Date currentDate = new Date(); // Current date/time
            if (truncateTime(checkInDate).equals(truncateTime(currentDate))) {
                Room room=roomRepository.findAllById(reserve_roomm.getRoom_id().getId());
                     System.out.println("elianananana");
                    System.out.print(checkInDate);
                    room.setStatus(roomStatus.RESERVED);
                System.out.print(roomStatus.RESERVED);
                    roomRepository.save(room);
                }
            }
        }
    private Date truncateTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

public  List<ReservationDTO> retrieveReservationForSpecificCustomer(Long id, String firstName) {
    User user = userRepository.findByNameOrId(firstName, id);
    if (user != null) {
        List<Reservation> reservationForCustomer = reservationRepository.findAllByUser(user);

        return  reservationForCustomer.stream().map(reservation -> ReservationMapper.mapToDTO(reservation)).collect(Collectors.toList());

    }
    else{
        throw new IllegalArgumentException("User Not Found");
    }
}




/*
   public ResponseEntity<?> reserveBooking(ReservationDTO request) {
        User userId = userRepository.findAllById(request.getUser_id());
        double totalPrice = 0;
        reservationStatus status = reservationStatus.Pending;
        List<Reserve_RoomDTO> reservedRooms = request.getBooking_room();

        if (checkNumberOfGuests(reservedRooms, request) == 0) {
            throw new IllegalArgumentException("Room capacity exceeded");
        }

        paymentMethod payment = request.getPaymentMethod();
        if (payment.compareTo(paymentMethod.PAY_LATER) == 0) {
            status = reservationStatus.Pending;
        } else if (payment.compareTo(paymentMethod.PAY_NOW) == 0) {
            status = reservationStatus.Confirmed;
        } else {
            throw new IllegalArgumentException("Payment method is required");
        }

        // Check if all rooms are available before proceeding
        for (Reserve_RoomDTO reserveRoomDTO : reservedRooms) {
            Room room = roomRepository.findAllById(reserveRoomDTO.getRoom_id());
            if (room.getStatus().compareTo(roomStatus.AVAILABLE) != 0) {
                return ResponseEntity.ok("ROOM IS NOT AVAILABLE");
            }
        }

        // Create and save the reservation
        Reservation reservation = Reservation.builder()
                .user(userId)
                .status(status)
                .num_children(request.getNum_children())
                .num_adults(request.getNum_adults())
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .payment_Method(payment)
                .booking_room(new ArrayList<>())
                .add_on(new ArrayList<>())
                .build();

        reservationRepository.save(reservation);

        List<Reserve_Add_OnDTO> additionList = request.getAdditions();
        for (Reserve_RoomDTO reserveRoomDTO : reservedRooms) {
            Room room = roomRepository.findAllById(reserveRoomDTO.getRoom_id());
            Reserve_Room reserve_room = Reserve_RoomMapper.toEntity(room, reservation);
            reservation.getBooking_room().add(reserve_room);
            room.setStatus(roomStatus.RESERVED);
            room.getBooking_room().add(reserve_room);
            reservationRoomRepository.save(reserve_room);
            totalPrice = totalPrice + room.getRoomType().getPrice();
            roomRepository.save(room);
        }
        long diffInMillies = Math.abs(reservation.getCheckOutDate().getTime() - reservation.getCheckInDate().getTime());
        int numberOfStay = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        for(int i=0;i< additionList.size();++i){
            Add_On addition=addOnRepository.findAllById(additionList.get(i).getAdd_on_id());
            Reserve_Add_On reserve_add_on= Reserve_Add_OnMapper.toEntity(reservation,addition);
            reservation.getAdd_on().add(reserve_add_on);
            addition.getAdd_on_id().add(reserve_add_on);
            totalPrice=totalPrice+(numberOfStay*addition.getPrice());
            reserve_Add_OnRepository.save(reserve_add_on);
            addOnRepository.save(addition);
        }

        Invoice invoice = InvoiceMapper.toEntity(reservation, totalPrice);
        invoiceRepository.save(invoice);
        return ResponseEntity.ok(InvoiceMapper.mapToInvoiceDetailsDTO(invoice));
    }
*/
    public ResponseEntity<?> confirmReservation( Long id){
        Reservation reservation=reservationRepository.findAllById(id);
        reservation.setStatus(reservationStatus.Confirmed);
        reservationRepository.save(reservation);
        return ResponseEntity.ok("Reservation confirmed");
    }
    public ResponseEntity<?> checkIn( Long id){
        Reservation reservation=reservationRepository.findAllById(id);
      if(reservation.getStatus().compareTo(reservationStatus.Confirmed)==0){
          reservation.setStatus(reservationStatus.Checkin);
          reservationRepository.save(reservation);
          return ResponseEntity.ok("Checked in successfully");
      }
          return ResponseEntity.ok("Please Confirmed The Reservation First");

    }
    public ResponseEntity<?> checkOut(long id) {
        Reservation reservation = reservationRepository.findAllById(id);
        for (int i = 0; i < reservation.getBooking_room().size(); ++i) {
            Room room = roomRepository.findAllById(reservation.getBooking_room().get(i).getRoom_id().getId());
            room.setStatus(roomStatus.AVAILABLE);
            roomRepository.save(room);
        }
        reservation.setStatus(reservationStatus.Checkout);
        return ResponseEntity.ok("Checked out successfully");
    }

    @Override
    public List<ReservationDTO> retrieveReservationsBySpecificDates(Date checkIn, Date checkOut) {

            List<Reservation> ReservationsBySpecificDates = reservationRepository.findAllReservationsInDates(checkIn,checkOut);
            if(!ReservationsBySpecificDates.isEmpty()) {
                return ReservationsBySpecificDates.stream().map(reservation -> ReservationMapper.mapToDTO(reservation)).collect(Collectors.toList());
            }
            else {
                throw new EntityNotFoundException("No reservations found in this period");
            }
    }




















  /*  public ResponseEntity<?> reserveBooking(ReservationDTO request) {
        User userId = userRepository.findAllById(request.getUser_id());
        double totalPrice = 0;
        reservationStatus status = reservationStatus.Pending;
        List<Reserve_RoomDTO> reservedRooms = request.getBooking_room();

        if (checkNumberOfGuests(reservedRooms, request) == 0) {
            return ResponseEntity.badRequest().body("Room capacity exceeded");
        }

        paymentMethod payment = request.getPaymentMethod();
        if (payment.compareTo(paymentMethod.PAY_LATER) == 0) {
            status = reservationStatus.Pending;
        } else if (payment.compareTo(paymentMethod.CREDIT_CARD) == 0) {
            status = reservationStatus.Confirmed;
        } else {
            throw new IllegalArgumentException("Payment method is required");
        }

        // Check if all rooms are available before proceeding
        for (Reserve_RoomDTO reserveRoomDTO : reservedRooms) {
            Room room = roomRepository.findAllById(reserveRoomDTO.getRoom_id());
            if (room.getStatus().compareTo(roomStatus.AVAILABLE) != 0) {
                throw new IllegalArgumentException("Room is not available");
            }
        }

        // Create and save the reservation
        Reservation reservation = Reservation.builder()
                .user(userId)
                .status(status)
                .num_children(request.getNum_children())
                .num_adults(request.getNum_adults())
                .checkInDate(request.getCheckInDate())
                .checkOutDate(request.getCheckOutDate())
                .payment_Method(payment)
                .booking_room(new ArrayList<>())
                .add_on(new ArrayList<>())
                .build();

        reservationRepository.save(reservation);

        List<Reserve_Add_On> addition = reservation.getAdd_on();
        calculateAdditionPrice(addition, totalPrice);

        for (Reserve_RoomDTO reserveRoomDTO : reservedRooms) {
            Room room = roomRepository.findAllById(reserveRoomDTO.getRoom_id());
            Reserve_Room reserve_room = Reserve_RoomMapper.toEntity(room, reservation);
            reservation.getBooking_room().add(reserve_room);
            room.setStatus(roomStatus.RESERVED);
            room.getBooking_room().add(reserve_room);
            reservationRoomRepository.save(reserve_room);
            totalPrice = totalPrice + room.getRoomType().getPrice();
            roomRepository.save(room);
        }

        Invoice invoice = InvoiceMapper.toEntity(reservation, totalPrice);
        invoiceRepository.save(invoice);
        return ResponseEntity.ok(InvoiceMapper.mapToInvoiceDetailsDTO(invoice));
    }
*/

    public double calculateroomPrice(List<Reserve_Room> room,double totalPrice){

        for(int i=0;i<room.size();++i){
            totalPrice=totalPrice+(room.get(i).getRoom_id().getRoomType().getPrice());
            System.out.print("totalllllt"+totalPrice);
        }
        return totalPrice;
    }
    public double calculateAdditionPrice(List<Reserve_Add_On> addition,double totalPrice,double numberOfStay){

        for(int i=0;i<addition.size();++i){
            totalPrice=totalPrice+(numberOfStay*addition.get(i).getAdd_on_id().getPrice());
            System.out.print("totalllllt"+totalPrice);
        }
        return totalPrice;
    }
    public ResponseEntity<?> cancelAnReservation(long id){
        Reservation reservation=reservationRepository.findAllById(id);
        reservation.setStatus(reservationStatus.pendingCancellationApproval);
        reservationRepository.save(reservation);
        return ResponseEntity.ok("Waiting Admin Approval");
    }

   /* public ResponseEntity<?> saveAdditionToSpecificBooking(List<Add_OnDTO> request,long id) {
        Reservation reservation=reservationRepository.findAllById(id);
        for(int i=0;i< request.size();++i){
            Add_On addition=addOnRepository.findAllById(request.get(i).getId());
            Reserve_Add_On reserve_add_on= Reserve_Add_OnMapper.toEntity(reservation,addition);
            reservation.getAdd_on().add(reserve_add_on);
            addition.getAdd_on_id().add(reserve_add_on);
            reserve_Add_OnRepository.save(reserve_add_on);
            addOnRepository.save(addition);
        }
        reservationRepository.save(reservation);
        long diffInMillies = Math.abs(reservation.getCheckOutDate().getTime() - reservation.getCheckInDate().getTime());
        int numberOfStay = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        double totalprice=calculateAdditionPrice()
        Invoice invoice=  invoiceRepository.findAllByBooking(reservation);
        invoice.setPrice(totalPrice);
        invoice=InvoiceMapper.update(modifiedReservation,invoice);
        return ResponseEntity.ok(InvoiceMapper.mapToInvoiceDetailsDTO(invoice));
    }
 /*   public ResponseEntity<?> updateAdditionToSpecificBooking(List<Add_OnDTO> request,long id) {
        Reservation reservation=reservationRepository.findAllById(id);
        List<Reserve_Add_On>previousAddition=reserve_Add_OnRepository.findAllByBooking(reservation);
        for(int i=0;i<request.size();++i){
            Add_On addition=addOnRepository.findAllById(request.get(i).getId());
            Reserve_Add_On reserve_add_on= Reserve_Add_OnMapper.update(reservation,addition,);
            reservation.getAdd_on().add(reserve_add_on);
            addition.getAdd_on_id().add(reserve_add_on);
            reserve_Add_OnRepository.save(reserve_add_on);
            addOnRepository.save(addition);
        }
        reservationRepository.save(reservation);
        return ResponseEntity.ok("Successfully addeddd");

    }*/

    /*public ResponseEntity<?> modifyBooking(long id,ReservationDTO request) {
        Reservation ToupdatedReservation=reservationRepository.findAllById(id);
        User userId = userRepository.findAllById(request.getUser_id());
        double totalPrice = 0;
        reservationStatus status = reservationStatus.Pending;
        List<Reserve_RoomDTO> reservedRooms = request.getBooking_room();
        if (checkNumberOfGuests(reservedRooms, request) == 0) {
            return ResponseEntity.badRequest().body("Room capacity exceeded");
        }
        paymentMethod payment = request.getPaymentMethod();
        if (payment.compareTo(paymentMethod.PAY_LATER) == 0) {
            status = reservationStatus.Pending;
        } else if (payment.compareTo(paymentMethod.CREDIT_CARD) == 0) {
            status = reservationStatus.Confirmed;
        } else {
            throw new IllegalArgumentException("Payment method is required");
        }
        Reservation modifiedReservation=ReservationMapper.update(ToupdatedReservation,request,userId,status);
        List<Reserve_Add_On> addition=ToupdatedReservation.getAdd_on();
        calculateAdditionPrice(addition,totalPrice);
      List<Reserve_Room> reserve_rooms= reservationRoomRepository.findAllByBooking(ToupdatedReservation);
        for(int i=0;i<request.getBooking_room().size();++i) {
            Room room = roomRepository.findAllById(request.getBooking_room().get(i).getRoom_id());
            boolean roomExists = reserve_rooms.stream()
                    .anyMatch(reserveRoom -> reserveRoom.getRoom_id().getId() == room.getId());
            System.out.print(roomExists);
            if (!roomExists) {
                if (room.getStatus().compareTo(roomStatus.AVAILABLE) == 0) {
                    Reserve_Room updatedreserve_room = Reserve_RoomMapper.toEntity(room,ToupdatedReservation);
                    ToupdatedReservation.getBooking_room().add(updatedreserve_room);
                    room.setStatus(roomStatus.RESERVED);
                    room.getBooking_room().add(updatedreserve_room);
                    reservationRoomRepository.save(updatedreserve_room);
                    totalPrice = totalPrice + room.getRoomType().getPrice();
                    roomRepository.save(room);
                } else {
                    return ResponseEntity.ok("ROOM IS NOT AVAILABLE");
                }

            }
        }
        reservationRepository.save(modifiedReservation);
       Invoice invoice=  invoiceRepository.findAllByBooking(modifiedReservation);
       invoice.setPrice(totalPrice);
       invoice=InvoiceMapper.update(modifiedReservation,invoice);
        return ResponseEntity.ok(InvoiceMapper.mapToInvoiceDetailsDTO(invoice));

    }*/

    public ResponseEntity<?> modifyBooking(long id,ReservationDTO request) {
        Reservation ToupdatedReservation=reservationRepository.findAllById(id);
        if(ToupdatedReservation==null){
            throw new EntityNotFoundException("Reservation ID not found");
        }
        User userId = userRepository.findAllById(request.getUser_id());
        double totalPrice = 0;
        reservationStatus status = reservationStatus.Pending;
        List<Reserve_RoomDTO> reservedRooms = request.getBooking_room();
        if (checkNumberOfGuests(reservedRooms, request) == 0) {
            return ResponseEntity.badRequest().body("Room capacity exceeded");
        }
        paymentMethod payment = request.getPaymentMethod();
        if (payment.compareTo(paymentMethod.PAY_LATER) == 0) {
            status = reservationStatus.Pending;
        } else if (payment.compareTo(paymentMethod.PAY_NOW) == 0) {
            status = reservationStatus.Confirmed;
        } else {
            throw new EntityNotFoundException("Payment method is required");
        }
        Reservation modifiedReservation=ReservationMapper.update(ToupdatedReservation,request,userId,status);

        List<Reserve_Room> reserve_rooms = reservationRoomRepository.findAllByBooking(ToupdatedReservation);
        List<Room> newBookingRooms = request.getBooking_room().stream()
                .map(bookingRoomRequest -> roomRepository.findAllById(bookingRoomRequest.getRoom_id()))
                .collect(Collectors.toList());

        for (Room room : newBookingRooms) {
            boolean roomExists = reserve_rooms.stream()
                    .anyMatch(reserveRoom -> reserveRoom.getRoom_id().getId() == room.getId());
            if (!roomExists) {
                if (isRoomAvailable(room, request.getCheckInDate(), request.getCheckOutDate())) {
                    Reserve_Room updatedreserve_room = Reserve_RoomMapper.toEntity(room, ToupdatedReservation);
                    ToupdatedReservation.getBooking_room().add(updatedreserve_room);
                    room.setStatus(roomStatus.RESERVED);
                    room.getBooking_room().add(updatedreserve_room);
                    reservationRoomRepository.save(updatedreserve_room);

                    roomRepository.save(room);
                } else {
                    throw new IllegalArgumentException("Room " + room.getId() + " is not available for the requested period");
                }
            }
        }

        for (Iterator<Reserve_Room> iterator = reserve_rooms.iterator(); iterator.hasNext();) {
            Reserve_Room existingReserveRoom = iterator.next();
            boolean existsInNewList = newBookingRooms.stream()
                    .anyMatch(newRoom -> newRoom.getId() == existingReserveRoom.getRoom_id().getId());

            if (!existsInNewList) {
                reservationRoomRepository.delete(existingReserveRoom);
                ToupdatedReservation.getBooking_room().remove(existingReserveRoom);
                iterator.remove();
            }
        }
        List<Reserve_Room> rooms=ToupdatedReservation.getBooking_room();
        totalPrice=totalPrice+calculateroomPrice(rooms,totalPrice);

        List<Reserve_Add_On> reserve_addon = reserve_Add_OnRepository.findAllByBooking(ToupdatedReservation);

        List<Add_On> newAdditions = request.getAdditions().stream()
                .map(additionRequest -> addOnRepository.findAllById(additionRequest.getAdd_on_id()))
                .collect(Collectors.toList());

        for (Add_On addition : newAdditions) {
            boolean additionExists = reserve_addon.stream()
                    .anyMatch(addon -> addon.getAdd_on_id().getId() == addition.getId());

            if (!additionExists) {
                Reserve_Add_On reserve_add_on = Reserve_Add_OnMapper.toEntity(ToupdatedReservation, addition);
                ToupdatedReservation.getAdd_on().add(reserve_add_on);
                reserve_Add_OnRepository.save(reserve_add_on);
            }
        }

        for (Iterator<Reserve_Add_On> iterator = reserve_addon.iterator(); iterator.hasNext();) {
            Reserve_Add_On existingReserveAddOn = iterator.next();
            boolean existsInNewList = newAdditions.stream()
                    .anyMatch(newAddition -> newAddition.getId() == existingReserveAddOn.getAdd_on_id().getId());

            if (!existsInNewList) {
                reserve_Add_OnRepository.delete(existingReserveAddOn);
                ToupdatedReservation.getAdd_on().remove(existingReserveAddOn);
                iterator.remove();
            }
        }

        List<Reserve_Add_On> addition=ToupdatedReservation.getAdd_on();
        long diffInMillies = Math.abs(ToupdatedReservation.getCheckOutDate().getTime() - ToupdatedReservation.getCheckInDate().getTime());
        int numberOfStay = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        totalPrice=calculateAdditionPrice(addition,totalPrice,numberOfStay);
        reservationRepository.save(modifiedReservation);
        Invoice invoice=  invoiceRepository.findAllByBooking(modifiedReservation);
        invoice=InvoiceMapper.update(modifiedReservation,invoice);
        invoice.setPrice(totalPrice);
        invoiceRepository.save(invoice);
        updateRoomStatusForReservations();
        return ResponseEntity.ok(InvoiceMapper.mapToInvoiceDetailsDTO(invoice));
    }




    public ResponseEntity<?> approveReservationCancellation(long id){
        Reservation reservation=reservationRepository.findAllById(id);
        if(reservation.getStatus().compareTo(reservationStatus.pendingCancellationApproval)==0){
            reservation.setStatus(reservationStatus.Canceled);
        }
        for(int i=0;i<reservation.getBooking_room().size();++i){
          Room roomToUpdate=RoomMapper.updateToAvailable(reservation.getBooking_room().get(i).getRoom_id());
          roomRepository.save(roomToUpdate);
        }
        reservationRepository.save(reservation);
        return ResponseEntity.ok("Reservation Canceled");
    }

    public List<RoomDTO> retrieveRoomsForSpecficReservation(long id){
        Reservation reservation =reservationRepository.findAllById(id);
        List<Reserve_Room> list_room=reservation.getBooking_room();
       return  list_room.stream().map(room -> Reserve_RoomMapper.mapToRoom(room)).collect(Collectors.toList());
    }



    public int checkNumberOfGuests(List<Reserve_RoomDTO> reservedRooms, ReservationDTO request) {
        List<Room> rooms = new ArrayList<>();
        int totalcapcity = 0;
        for (Reserve_RoomDTO reserveRoom : reservedRooms) {
            Room room = roomRepository.findAllById(reserveRoom.getRoom_id());
              totalcapcity=totalcapcity+room.getRoomType().getNum_adults()+room.getRoomType().getNum_children();
                System.out.print(totalcapcity);
        }
        int remainingCapcity = request.getNum_adults() +request.getNum_children();
        if((totalcapcity-remainingCapcity)<0) {
            return 0;
        }
        else
        {
            return 1;
        }

    }














}
