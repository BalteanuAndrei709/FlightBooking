package com.notificationservice.service;

import com.notificationservice.model.Booking;
import com.notificationservice.model.Notification;
import com.notificationservice.model.NotificationStatus;
import com.notificationservice.repository.NotificationsRepository;
import com.notificationservice.repository.ReceivedBookingRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceivedBookingsService {

		private final ReceivedBookingRepository receivedBookingRepository;
		private final NotificationsRepository notificationsRepository;

		public ReceivedBookingsService(ReceivedBookingRepository receivedBookingRepository, NotificationsRepository notificationsRepository) {
				this.receivedBookingRepository = receivedBookingRepository;
				this.notificationsRepository = notificationsRepository;
		}

		public void saveReceivedBooking(Booking booking){
				receivedBookingRepository.save(booking);
		}

		@Scheduled(cron="0 * * * * ?")
		public void checkIfBookingArrivedToUser(){
				Iterable<Booking> listOfBookings = receivedBookingRepository.findAll();
				List<Integer> listOfIds = new ArrayList<>();

				listOfBookings
											.forEach(booking->{
													Integer id = booking.getFlightId();
															listOfIds.add(id);
				});

				listOfIds
								 .forEach(id ->{
											if(notificationsRepository.findByRecipientIdAndStatus(id, NotificationStatus.DELIVERED).isPresent()){
													receivedBookingRepository.deleteById(String.valueOf(id));
										}
								});
		}
}
