package com.ssafy.hallhole.twitter.service;

import com.ssafy.hallhole.twitter.Twitter;
import com.ssafy.hallhole.twitter.repository.TwitterRepository;
import io.github.redouane59.twitter.TwitterClient;
import io.github.redouane59.twitter.dto.endpoints.AdditionalParameters;
import io.github.redouane59.twitter.dto.tweet.TweetList;
import io.github.redouane59.twitter.dto.tweet.TweetV2;
import io.github.redouane59.twitter.dto.user.UserV2;
import io.github.redouane59.twitter.signature.TwitterCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TwitterServiceImpl implements TwitterService {

    private final TwitterRepository twitterRepository;

    private final Environment env;


    @Override
    @Scheduled(cron = "0 0/1 * * * ?")
    public void TestTwitterLoading() {
        LocalDateTime endLocalDateTime = LocalDateTime.now();
//        LocalDateTime startLocalDateTime = endLocalDateTime.minusMinutes(5);
        LocalDateTime startLocalDateTime = endLocalDateTime.minusDays(1);
        AdditionalParameters additionalParameters = AdditionalParameters.builder().startTime(startLocalDateTime).endTime(endLocalDateTime).build();

        TwitterClient twitterClient = new TwitterClient(TwitterCredentials.builder()
                .accessToken(env.getProperty("twitterAccessToken"))
                .accessTokenSecret(env.getProperty("twitterAccessTokenSecret"))
                .apiKey(env.getProperty("twitterApiKey"))
                .apiSecretKey(env.getProperty("twitterApiSecret"))
                .build());

        UserV2 userV2 = twitterClient.getUserFromUserName("TicketOpen"); //@를 제외한 아이디

        TweetList tweetList = twitterClient.getUserTimeline(userV2.getId(), additionalParameters);
        for (TweetV2.TweetData tweet : tweetList.getData()) {
            if (tweet.getText().contains("블루스퀘어")) {
                if (tweet.getText().contains("콘서트") || tweet.getText().contains("팬미팅") || tweet.getText().contains("어워즈")) {
                    continue;
                }
            } else {
                if (!tweet.getText().contains("뮤지컬") && !tweet.getText().contains("연극") && !tweet.getText().contains("극단"))
                    continue;
            }
            Twitter tweetData = Twitter.builder().id(tweet.getId()).contents(tweet.getText()).time(tweet.getCreatedAt()).build();
//            saveTwitter((tweetData));
           }
    }

    @Override
    public void saveTwitter(Twitter twitter) {
        if (findOne(twitter.getId()).isEmpty()) {
            twitterRepository.save(twitter);
        }
    }

    @Override
    public Optional<Twitter> findOne(String id) {
        return Optional.ofNullable(twitterRepository.findTwitterById(id));
    }

    @Override
    public List<Twitter> findAllTweet() {
        return twitterRepository.findAll();
    }
}
