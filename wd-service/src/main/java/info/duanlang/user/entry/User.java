package info.duanlang.user.entry;

import info.duanlang.base.bean.BasicBean;
import java.util.Date;
import javax.persistence.*;

@Table(name = "wd_user")
public class User extends BasicBean {
    @Id
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "head_img")
    private String headImg;

    @Column(name = "time_created")
    private Date timeCreated;

    @Column(name = "time_updated")
    private Date timeUpdated;

    private Integer status;

    private String mobile;

    private String password;

    public User(Long id, String userName, String realName, String headImg, Date timeCreated, Date timeUpdated, Integer status, String mobile, String password) {
        this.id = id;
        this.userName = userName;
        this.realName = realName;
        this.headImg = headImg;
        this.timeCreated = timeCreated;
        this.timeUpdated = timeUpdated;
        this.status = status;
        this.mobile = mobile;
        this.password = password;
    }

    public User() {
        super();
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * @return real_name
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     * @return head_img
     */
    public String getHeadImg() {
        return headImg;
    }

    /**
     * @param headImg
     */
    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    /**
     * @return time_created
     */
    public Date getTimeCreated() {
        return timeCreated;
    }

    /**
     * @param timeCreated
     */
    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    /**
     * @return time_updated
     */
    public Date getTimeUpdated() {
        return timeUpdated;
    }

    /**
     * @param timeUpdated
     */
    public void setTimeUpdated(Date timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}