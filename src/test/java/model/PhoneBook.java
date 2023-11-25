package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    @JsonProperty("BookName")
    private String bookName;
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    @JsonProperty("chapter")
    private Map<Character, List<Contact>> chapter;
    public Map<Character, List<Contact>> getChapter() {
        return chapter;
    }
    public void setChapter(Map<Character, List<Contact>> chapter) {
        this.chapter = chapter;
    }

    public static class Contact {
        @JsonProperty("Name")
        private String name;

        @JsonProperty("PhoneNum")
        private String phoneNum;

        @JsonProperty("Comment")
        private String comment;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getPhoneNum() {
            return phoneNum;
        }
        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }
        public String getComment() {
            return comment;
        }
        public void setComment(String comment) {
            this.comment = comment;
        }
    }

}
