package main.java.com.journalapp.util;

import main.java.com.journalapp.model.Entry;
import main.java.com.journalapp.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Represent a user session.
 */
public final class Session {
    private static User currentUser = null;

    /**
     * Check if there is user logged in currently.
     * @return true if there is user logged in. false if otherwise
     */
    public static boolean hasActiveUser() {
        return currentUser != null;
    }

    /**
     * Create and record a new user. Then if successful, login to the newly created account.
     * @return true if signup success. false if user already exists
     */
    public static boolean signup(String username,  String email, String password) {
        ArrayList<User> userList = UserList.read();
        for (User user : userList) {
            if (user.getEmail().equals(email))  // one account per email
                return false;
        }
        User user = new User(UUID.randomUUID().toString(), username, email, password, null);
        currentUser = user;
        userList.add(user);
        UserList.write(userList);  // update user list data
        return true;
    }

    /**
     * Login into existing user account with given email and password.
     * <p>
     * Suggestion: If this method returns false, display the message "Incorrect email and password combination".
     * For the sake of simplicity, I am not including a way to tell the cause of login failure, e.g. is the email
     * entered not registered? or is the password incorrect?
     * @return true if login success. false if otherwise
     */
    public static boolean login(String email, String password) {
        ArrayList<User> users = UserList.read();
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    /**
     * Log out current user/remove active user.
     */
    public static void logout() {
        currentUser = null;
    }

    /**
     * Return the username of current logged account. Use this for addressing the user.
     * @return string representing username
     */
    public static String getUsername() {
        return hasActiveUser() ? currentUser.getUsername() : null;
    }

    /**
     * Return the email address of current logged account.
     * @return string representing email address
     */
    public static String getEmail() {
        return hasActiveUser() ? currentUser.getEmail() : null;
    }

    /**
     * Return the password of current logged account.
     * @return string representing password
     */
    public static String getPassword() {
        return hasActiveUser() ? currentUser.getPassword() : null;
    }

    /**
     * Return list of existing journal entries saved previously for the current logged user.
     * @return ArrayList containing Entry objects representing individual entries
     */
    public static ArrayList<Entry> listEntries() {
        return hasActiveUser() ? currentUser.getEntries().listEntries() : null;
    }

    /**
     * Create and save entry for the current logged user.
     * @param date the date for this entry. type of LocalDate
     * @param content string of content for this entry
     * @param mood "positive" or "negative". call TODO: Implement get mood method
     * @param weather string representing weather (Sunny, Rainy, Hazy, Thunderstorms). hint: call Weather.getCurrentWeather
     */
    public static void createEntry(LocalDate date, String content, String mood, String weather) {
        currentUser.getEntries().createEntry(date, content, mood, weather);
    }

    /**
     * Delete an entry with the given id. (Irreversible, use at caution)
     * @param id id of target entry
     */
    public static void deleteEntry(String id) {
        currentUser.getEntries().deleteEntry(id);
    }

    /**
     * Edit existing entry given the id of that entry, and also the contents to be written.
     * @param id the unique id for that entry
     * @param date the date for that entry. type of LocalDate
     * @param content string of content for that entry
     * @param mood "positive" or "negative". call TODO: Implement get mood method
     * @param weather string representing weather (Sunny, Rainy, Hazy, Thunderstorms). hint: call Weather.getCurrentWeather
     */
    public static void editEntry(String id, LocalDate date, String content, String mood, String weather) {
        currentUser.getEntries().editEntry(id, date, content, mood, weather);
    }

    static void main(String[] args) {
        System.out.println("Active: " + hasActiveUser());
        //signup("cha", "ahcha@gmail.com", "verysecurepassword");
        login("ahcha@gmail.com", "verysecurepassword");
        System.out.println("Active: " + hasActiveUser());
        createEntry(LocalDate.now(), "today i did something", "positive", Weather.getCurrentWeather());
        System.out.println(listEntries());
        //deleteEntry(id)  // use id from the csv file for current user
        logout();
        System.out.println("Active: " + hasActiveUser());
    }
}
