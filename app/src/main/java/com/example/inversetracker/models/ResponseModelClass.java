package com.example.inversetracker.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseModelClass {

    private String message;
    @SerializedName("auth_token")
    private String authToken;
    @SerializedName("firstname")
    private String firstname;
    @SerializedName("lastname")
    private String lastname;
    @SerializedName("patronymic")
    private String patronymic;
    @SerializedName("age")
    private String age;
    @SerializedName("school_class")
    private String schoolClass;
    @SerializedName("number")
    private String numberClass;
    @SerializedName("litera")
    private String litera;
    @SerializedName("title")
    private String title;
    @SerializedName("text")
    private String description;
    @SerializedName("role")
    private RoleResponse role;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String descriptionText;
    @SerializedName("category")
    private CategoryResponse category;
    @SerializedName("teacher")
    private TeacherResponse teacher;
    @SerializedName("place")
    private String place;
    @SerializedName("id")
    private String idResponse;
    @SerializedName("groups")
    private List<ResponseModelClass> groups;
    @SerializedName("schedule")
    private List<ResponseModelClass> schedule;
    @SerializedName("day")
    private String day;
    @SerializedName("time")
    private String time;
    @SerializedName("document")
    private String document;
    @SerializedName("limit")
    private String limit;
    @SerializedName("members")
    private List<ResponseModelClass> members;
    @SerializedName("open")
    private boolean open;

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public List<ResponseModelClass> getMembers() {
        return members;
    }

    public void setMembers(List<ResponseModelClass> members) {
        this.members = members;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<ResponseModelClass> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<ResponseModelClass> schedule) {
        this.schedule = schedule;
    }

    public List<ResponseModelClass> getGroups() {
        return groups;
    }

    public void setGroups(List<ResponseModelClass> groups) {
        this.groups = groups;
    }

    public String getIdResponse() {
        return idResponse;
    }

    public void setIdResponse(String idResponse) {
        this.idResponse = idResponse;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public TeacherResponse getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherResponse teacher) {
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public CategoryResponse getCategory() {
        return category;
    }

    public void setCategory(CategoryResponse category) {
        this.category = category;
    }

    public RoleResponse getRole() {
        return role;
    }

    public void setRole(RoleResponse role) {
        this.role = role;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumberClass() {
        return numberClass;
    }

    public void setNumberClass(String numberClass) {
        this.numberClass = numberClass;
    }

    public String getLitera() {
        return litera;
    }

    public void setLitera(String litera) {
        this.litera = litera;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(String school_class) {
        this.schoolClass = school_class;
    }



    public ResponseModelClass(){

    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseModelClass(String message, String authToken) {
        this.message = message;
        this.authToken = authToken;
    }
}
