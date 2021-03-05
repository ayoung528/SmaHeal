package com.ssafy.smaheal.controller;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.PageRequest;


import io.swagger.annotations.ApiOperation;

import com.ssafy.smaheal.model.Donation;
import com.ssafy.smaheal.model.Smile;
import com.ssafy.smaheal.repository.DonationRepository;
import com.ssafy.smaheal.repository.SmileRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/smile")
public class SmileController {

	@Autowired
	private SmileRepository smileRepository;
	@Autowired
	private DonationRepository donationRepository;
	public static List camList = new LinkedList<>();
	public static List selfList = new LinkedList<>();
	public static List textList = new LinkedList<>();
	public static String autoFile;

	@GetMapping("/cameraOn")
	@ApiOperation(value = "웹캠 on")
	public Object cameraOn() throws SQLException, IOException {
		try {
			System.out.println("Camera on Python Call");
			String[] command = new String[2];
			command[0] = "python";
			// 경로 확인
			String hostname = InetAddress.getLocalHost().getHostName();
			if (hostname.substring(0, 7).equals("DESKTOP")) {// local
				command[1] = "./backend/cameraOn.py";
			} else {// aws
				command[1] = "../cameraOn.py";
			}
			// command[2] = "./backend/files/haarcascade_frontalface_default.xml";
			// command[3] = "./backend/files/emotion_model.hdf5";
			try {
				execPython(command);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<>(camList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/regist")
	@ApiOperation(value = "웃음 기부 등록")
	public Object registDonation(@RequestBody Smile request) throws SQLException, IOException {
		try {
			Smile smile = new Smile();
			smile.setUserId(request.getUserId());
			smile.setDonationid(request.getDonationid());
			smile.setPhoto(request.getPhoto());
			smile.setSmileper(request.getSmileper());
			smile.setComment(request.getComment());
			smile.setAgreement(request.getAgreement());
			smile.setTitle(donationRepository.findByDonationid(request.getDonationid()).getTitle());
			smileRepository.save(smile);

			Donation donation = new Donation();
			donation = donationRepository.findByDonationid(request.getDonationid());
			int nowCnt = donation.getNowcnt();
			donation.setNowcnt(nowCnt + 1);
			donationRepository.save(donation);
			
			return new ResponseEntity<>(smile, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/autoRegist")
	@ApiOperation(value = "웃음 기부 등록")
	public Object autoRegistDonation(@RequestBody Smile request) throws SQLException, IOException {
		try {
			Smile smile = new Smile();
			smile.setUserId(request.getUserId());
			smile.setDonationid(request.getDonationid());
			smile.setPhoto(request.getPhoto());
			smile.setSmileper(request.getSmileper());
			smile.setComment(request.getComment());
			smile.setAgreement(request.getAgreement());
			smile.setTitle(donationRepository.findByDonationid(request.getDonationid()).getTitle());
			smileRepository.save(smile);

			Donation donation = new Donation();
			donation = donationRepository.findByDonationid(request.getDonationid());
			int nowCnt = donation.getNowcnt();
			donation.setNowcnt(nowCnt + 1);
			donationRepository.save(donation);

			return new ResponseEntity<>(smile, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/cameraOff")
	@ApiOperation(value = "웹캠 off")
	public Object cameraOff() throws SQLException, IOException, ExecuteException {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM Python.exe");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@PostMapping("/smileCheck")
	@ApiOperation("스마일 체크")
	public Object smileCheck(@RequestBody String filename) throws SQLException, IOException {
		try {
			String tempFileName = createFile(filename);
			System.out.println("SmileCheck Python Call");
			String[] command = new String[3];
			command[0] = "python";
			// 경로 확인
			String hostname = InetAddress.getLocalHost().getHostName();
			if (hostname.substring(0, 7).equals("DESKTOP")) {// local
				command[1] = "./backend/imageCheck.py";
			} else {// aws
				command[1] = "../imageCheck.py";
			}
			command[2] = tempFileName;
			try {
				execPythonSmileCheck(command);

				return new ResponseEntity<>(selfList, HttpStatus.OK);
			} catch (Exception e) {
				return "findFail";
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/autoCheck")
	@ApiOperation("자동캡쳐 파일 체크")
	public Object autoCheck(@RequestBody String filename) throws SQLException, IOException {
		try {
			String tempFileName = createFile(filename);
			System.out.println("autoCheck Python Call");
			String[] command = new String[3];
			command[0] = "python";
			// 경로 확인
			String hostname = InetAddress.getLocalHost().getHostName();
			if (hostname.substring(0, 7).equals("DESKTOP")) {// local
				command[1] = "./backend/autoCheck.py";
			} else {// aws
				command[1] = "../autoCheck.py";
			}
			command[2] = tempFileName;
			try {
				execPythonAutoCheck(command);
				return new ResponseEntity<>(autoFile, HttpStatus.OK);
			} catch (Exception e) {
				return "findFail";
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public static void execPythonAutoCheck(String[] command) throws IOException, InterruptedException {
		CommandLine commandLine = CommandLine.parse(command[0]);

		for (int i = 1, n = command.length; i < n; i++) {
			commandLine.addArgument(command[i]);
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(outputStream);
		DefaultExecutor executor = new DefaultExecutor();
		executor.setStreamHandler(pumpStreamHandler);
		int result = executor.execute(commandLine);
		System.out.println("result: " + result);
		System.out.println("output: " + outputStream.toString());

		String[] outputList = outputStream.toString().split("\n");
		int len = outputList.length;
		String filename = outputList[len - 1].trim();
		autoFile = filename;
	}

	public static void execPythonSmileCheck(String[] command) throws IOException, InterruptedException {
		CommandLine commandLine = CommandLine.parse(command[0]);
		for (int i = 1, n = command.length; i < n; i++) {
			commandLine.addArgument(command[i]);
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(outputStream);
		DefaultExecutor executor = new DefaultExecutor();
		executor.setStreamHandler(pumpStreamHandler);
		int result = executor.execute(commandLine);
		System.out.println("result: " + result);
		System.out.println("output: " + outputStream.toString());

		String[] outputList = outputStream.toString().split("\n");
		int len = outputList.length;
		String filename = outputList[len - 1].trim();
		String emotion = outputList[len - 2].trim();
		String happyPer = outputList[len - 3].trim();
		selfList.clear();
		selfList.add(filename);
		selfList.add(emotion);
		selfList.add(happyPer);
	}

	public static void execPython(String[] command) throws IOException, InterruptedException {
		CommandLine commandLine = CommandLine.parse(command[0]);
		for (int i = 1, n = command.length; i < n; i++) {
			commandLine.addArgument(command[i]);
		}

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(outputStream);
		DefaultExecutor executor = new DefaultExecutor();
		executor.setStreamHandler(pumpStreamHandler);
		int result = executor.execute(commandLine);
		System.out.println("result: " + result);

		String[] outputList = outputStream.toString().split("\n");
		int len = outputList.length;
		if (outputList[len - 1].length() < 6) {
			String fileName = outputList[len - 2].trim();
			String percent = outputList[len - 1].trim();
			camList.clear();
			camList.add(fileName);
			camList.add(percent);
			System.out.println(fileName);
			System.out.println(percent);
		} else {
			camList.add("cancel");
		}
		System.out.println("output: " + outputStream.toString().split("\n")[0]);
	}

	public static String createFile(String filename) throws FileNotFoundException {
		long time = System.currentTimeMillis();
		String name = Long.toString(time);
		// 경로 정해주기
		try {
			File file = null;
			String hostname = InetAddress.getLocalHost().getHostName();
			if (hostname.substring(0, 7).equals("DESKTOP")) {// local
				file = new File("./frontend/public/textFiles/" + name);
			} else {// aws
				file = new File("../../frontend/public/textFiles/" + name);
			}
			String str = filename;
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(str);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return name;
	}

	@GetMapping("/textCheck/{text}")
	@ApiOperation(value = "응원메세지 감성분석")
	public Object textCheck(@PathVariable String text) throws SQLException, IOException {
		String text_res = "";
		System.out.println("Text Check Python Call");
		String[] command = new String[3];
		command[0] = "python";
		// 경로 확인
		String hostname = InetAddress.getLocalHost().getHostName();
		if (hostname.substring(0, 7).equals("DESKTOP")) {// local
			command[1] = "./backend/textCheck.py";
			System.out.println("in");
		} else {// aws
			command[1] = "../textCheck.py";
		}
		command[2] = text;
		try {
			text_res = execPython2(command);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(text_res, HttpStatus.OK);
	}

	@GetMapping("/smileKing")
	@ApiOperation(value = "웃음왕 Top3")
	public Object getSmileKing() throws SQLException, IOException, ExecuteException {
		try {
			List<Smile> top3 = new ArrayList<Smile>();
			String no1 = null;
			String no2 = null;

			List<Smile> list = smileRepository.findByOrderBySmileperDesc();
			YearMonth ym = YearMonth.from(LocalDate.now());
			
			LocalDate ldS = ym.minusMonths(2).atEndOfMonth();
			LocalDate ldE = ym.atDay(1);
			
			List<Smile> lastMonthList = new ArrayList<Smile>();
			
			for (Smile smile : list) {
				if(smile.getCreatedate().isAfter(ldS) && smile.getCreatedate().isBefore(ldE)) {
					lastMonthList.add(smile);
				}
			}
			
			for (Smile smile : lastMonthList) {
				if (no1 == null) {
					no1 = smile.getUserId();
					top3.add(smile);
				} else if (no2 == null) {
					if (!smile.getUserId().equals(no1)) {
						no2 = smile.getUserId();
						top3.add(smile);
					}
				} else {
					if (!smile.getUserId().equals(no1) && !smile.getUserId().equals(no2)) {
						top3.add(smile);
						break;
					}
				}
			}

			return top3;
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public static String execPython2(String[] command) throws IOException, InterruptedException {
		CommandLine commandLine = CommandLine.parse(command[0]);
		for (int i = 1, n = command.length; i < n; i++) {
			commandLine.addArgument(command[i]);
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(outputStream);
		DefaultExecutor executor = new DefaultExecutor();
		executor.setStreamHandler(pumpStreamHandler);
		int result = executor.execute(commandLine);
		System.out.println("result: " + result);
		String[] outputList = outputStream.toString().split("\n");
		return outputList[outputList.length - 1];
	}

	@GetMapping("/getSmileCnt")
	@ApiOperation(value = "누적,오늘 웃음사진 개수")
	public Object getSmileCnt() throws SQLException, IOException, ExecuteException {
		LocalDate currentDate = LocalDate.now();
		List<Integer> result = new LinkedList<>();
		try {
			List<Smile> smileList = smileRepository.findAll();
			List<Smile> todayList = smileRepository.findByCreatedate(currentDate);
			result.add(smileList.size());
			result.add(todayList.size());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getTodayCnt")
	@ApiOperation(value = "오늘 웃음사진 개수")
	public Object getTodayCnt() throws SQLException, IOException, ExecuteException {
		LocalDate currentDate = LocalDate.now();
		try {
			List<Smile> todayList = smileRepository.findByCreatedate(currentDate);
			return new ResponseEntity<>(todayList.size(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getMySmile/{userId}/{page}")
	@ApiOperation(value = "내 웃음사진")
	public Object getMySmile(@PathVariable String userId, @PathVariable int page) throws SQLException, IOException {
		try {
			List<Smile> mySmileList = smileRepository.findByUserIdOrderBySmileidDesc(userId, PageRequest.of(page, 8));
			if (mySmileList != null)
				return new ResponseEntity<>(mySmileList, HttpStatus.OK);
			else
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getMySmileCnt/{userId}")
	@ApiOperation(value = "내 웃음사진 개수")
	public Object getMySmileCnt(@PathVariable String userId) throws SQLException, IOException {
		try {
			List<Smile> mySmileList = smileRepository.findByUserId(userId);
			if (mySmileList != null)
				return new ResponseEntity<>(mySmileList.size(), HttpStatus.OK);
			else
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
