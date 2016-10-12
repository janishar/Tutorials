package ali.nestedplaceholderview.demo.nestedplaceholderview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by janisharali on 03/10/16.
 */

public class GoogleNews {

    public static String URL = "http://ajax.googleapis.com/ajax/services/feed/load?v=1.0&num=20&q=http%3A%2F%2Fnews.google.com%2Fnews%3Foutput%3Drss";

    @SerializedName("responseData")
    @Expose
    private responseData response;

    public responseData getResponse() {
        return response;
    }

    public void setResponse(responseData response) {
        this.response = response;
    }

    public class responseData{
        @SerializedName("feed")
        @Expose
        private feed feedData;

        @SerializedName("responseStatus")
        @Expose
        private Integer responseStatus;

        public feed getFeedData() {
            return feedData;
        }

        public void setFeedData(feed feedData) {
            this.feedData = feedData;
        }

        public Integer getResponseStatus() {
            return responseStatus;
        }

        public void setResponseStatus(Integer responseStatus) {
            this.responseStatus = responseStatus;
        }
    }

    public class feed{
        @SerializedName("feedUrl")
        @Expose
        private String feedUrl;

        @SerializedName("title")
        @Expose
        private String title;

        @SerializedName("entries")
        @Expose
        private List<entry> entryList;

        public String getFeedUrl() {
            return feedUrl;
        }

        public void setFeedUrl(String feedUrl) {
            this.feedUrl = feedUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<entry> getEntryList() {
            return entryList;
        }

        public void setEntryList(List<entry> entryList) {
            this.entryList = entryList;
        }
    }

    public class entry{
        @SerializedName("title")
        @Expose
        private String title;

        @SerializedName("publishedDate")
        @Expose
        private String publishedDate;

        @SerializedName("contentSnippet")
        @Expose
        private String contentSnippet;

        @SerializedName("content")
        @Expose
        private String content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPublishedDate() {
            return publishedDate;
        }

        public void setPublishedDate(String publishedDate) {
            this.publishedDate = publishedDate;
        }

        public String getContentSnippet() {
            return contentSnippet;
        }

        public void setContentSnippet(String contentSnippet) {
            this.contentSnippet = contentSnippet;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
