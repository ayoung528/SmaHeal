package com.ssafy.smaheal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ssafy.smaheal.model.Youtube;
import com.ssafy.smaheal.repository.FunnyRepository;
import com.ssafy.smaheal.repository.YoutubeRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/crawling")
public class CrawlingController {
    @Autowired
    YoutubeRepository youtubeRepository;

    private Object API_KEY ="AIzaSyDEV2OseI-e_KMQdYgTLQOO5rPZ3_Bf5Sg";

    @PostMapping("/youtube")
    @ApiOperation("유튜브 크롤링")
    public void search(@RequestParam("search") String search, @RequestParam("age") int age) throws SQLException, IOException {
        String apiurl = "https://www.googleapis.com/youtube/v3/search";
        apiurl += "?key="+API_KEY;
        apiurl += "&part=snippet&type=video&maxResults=5&videoEmbeddable=true";
        apiurl += "&q="+URLEncoder.encode(search,"UTF-8");
        URL url = new URL(apiurl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));

        String line = "";
        String result = "";

        while ((line = br.readLine()) != null) {
            result += line;
        }

        // Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성      
        JsonParser parser = new JsonParser();
        JsonObject jsonObj = (JsonObject)parser.parse(result);
        JsonArray itemArray = (JsonArray) jsonObj.get("items");
        
        for (JsonElement e : itemArray) {
            // "https://www.youtube.com/embed/"
            String vid = e.getAsJsonObject().get("id").getAsJsonObject().get("videoId").getAsString();
            String thumbnail = e.getAsJsonObject().get("snippet").getAsJsonObject().get("thumbnails").getAsJsonObject().get("high").getAsJsonObject().get("url").getAsString();
            String title = e.getAsJsonObject().get("snippet").getAsJsonObject().get("title").getAsString();
            // System.out.println(title);
            // System.out.println(vid);
            // System.out.println(thumbnail);

            if(youtubeRepository.findByVideoIdAndAges(vid, age) == null) {
                Youtube youtube = new Youtube();
                youtube.setVideoId(vid);
                youtube.setThumbnail(thumbnail);
                youtube.setAges(age);
                youtube.setTitle(title);
                youtubeRepository.save(youtube);
            }
        }
    }

    @GetMapping("/getVideos")
    @ApiOperation("동영상 리스트 가져오기")
    public Object getVideos() throws SQLException, IOException {
        try {
            return new ResponseEntity<>(youtubeRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getVideosByAge/{ages}")
    @ApiOperation("연령별 동영상 리스트 가져오기")
    public Object getVideos(@PathVariable int ages) throws SQLException, IOException {
        try {
            List<Youtube> list = youtubeRepository.findByAges(ages);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getVideosP/{page}")
    @ApiOperation("동영상 리스트 가져오기 페이징")
    public Object getVideosPage(@PathVariable int page) throws SQLException, IOException {
        try {
            System.out.println(youtubeRepository.findAll(PageRequest.of(page, 8)));
            return new ResponseEntity<>(youtubeRepository.findAll(PageRequest.of(page, 8)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getVideosByAgeP/{ages}/{page}")
    @ApiOperation("연령별 동영상 리스트 가져오기 페이징")
    public Object getVideosPage(@PathVariable int ages, @PathVariable int page) throws SQLException, IOException {
        try {
            List<Youtube> list = youtubeRepository.findByAges(ages, PageRequest.of(page, 8));
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/deleteVideo/{youtubeid}")
    @ApiOperation("비디오 삭제")
    public Object deleteVideo(@PathVariable Long youtubeid) throws SQLException, IOException {
        try {
            youtubeRepository.deleteById(youtubeid);
            return new ResponseEntity<>("delete" + youtubeid, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getVideoCnt")
    @ApiOperation("동영상 개수")
    public Object getVideoCnt() throws SQLException, IOException {
        try {
            int cnt = youtubeRepository.findAll().size();
            return new ResponseEntity<>(cnt, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    FunnyRepository funnyRepo;

    @GetMapping("/funImage")
    @ApiOperation("재밌는 사진 가져오기")
    public Object funImage() throws SQLException, IOException {
        try {
            return new ResponseEntity<>(funnyRepo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
