package com.ssafy.smaheal;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ssafy.smaheal.help.ApplicationContextProvider;
import com.ssafy.smaheal.model.Donation;
import com.ssafy.smaheal.repository.DonationRepository;

@SpringBootApplication
public class SmahealApplication {

	static Calendar date;
	static String time;

	public static void main(String[] args) {
		SpringApplication.run(SmahealApplication.class, args);

		// 24시마다 마감된 게시물을 가져오고 일련번호를 부여하기 위함
		DonationRepository donationRepository = ApplicationContextProvider.getApplicationContext()
				.getBean(DonationRepository.class);
		date = Calendar.getInstance();
		Timer timer = new Timer();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		SimpleDateFormat todayF = new SimpleDateFormat("yyyy-MM-dd");
		time = format.format(date.getTime());

		TimerTask tt = new TimerTask() {
			@Override
			public void run() {
				date = Calendar.getInstance();
				time = format.format(date.getTime());
				String today = todayF.format(date.getTime());

				if (time.equals("00:00")) {
					List<Donation> list = donationRepository.findByApprovalAndTempOrderByCreatedateDesc(1, 0);
					
					for (Donation donation : list) {
						String eDate = donation.getEdate().toString();
						if (eDate.equals(today)) {

							donation.setApproval(2);
							String uid = UUID.randomUUID().toString();
							donation.setTemplate(uid);
							donationRepository.save(donation);

						}
					}
				}
			}
		};

		timer.scheduleAtFixedRate(tt, 0, 10 * 6000);
	}

}
