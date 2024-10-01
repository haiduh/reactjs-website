package com.github.group37.roadmap.other;

import com.github.group37.roadmap.percistance.models.TopicDao;
import com.github.group37.roadmap.percistance.models.UserTopicsDao;

import java.util.List;

public class UserTopicsRequest {

    public List<UserTopic> userTopics;//todo might need to make usertopicdao into a seperate class

    public List<UserTopic> getUserTopics() {
        return userTopics;
    }

    public void setUserTopics(List<UserTopic> userTopics) {
        this.userTopics = userTopics;
    }

}
