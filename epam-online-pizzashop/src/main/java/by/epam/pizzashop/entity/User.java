package by.epam.pizzashop.entity;

import java.util.Objects;

/**
 * The type User.
 */
public class User {
    private long userId;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private Role role;
    private Status status;


    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param userId    the user id
     * @param login     the login
     * @param password  the password
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email
     * @param telephone the telephone
     * @param role      the role
     * @param status    the status
     */
    public User(long userId, String login, String password, String firstName,
                String lastName, String email, String telephone, Role role, Status status) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
        this.role = role;
        this.status = status;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets telephone.
     *
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Sets telephone.
     *
     * @param telephone the telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(login, user.login)
                && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email)
                && Objects.equals(telephone, user.telephone) && role == user.role && status == user.status;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result *= 31 + (login != null ? login.hashCode() : 0);
        result *= 31 + (password != null ? password.hashCode() : 0);
        result *= 31 + (firstName != null ? firstName.hashCode() : 0);
        result *= 31 + (lastName != null ? lastName.hashCode() : 0);
        result *= 31 + (email != null ? email.hashCode() : 0);
        result *= 31 + (telephone != null ? telephone.hashCode() : 0);
        result *= 31 + (status != null ? status.hashCode() : 0);
        result *= 31 + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User{");
        builder.append("userId=").append(userId);
        builder.append(", login=").append(login);
        builder.append(", password=").append(password);
        builder.append(", firstName=").append(firstName);
        builder.append(", lastName=").append(lastName);
        builder.append(", email=").append(email);
        builder.append(", telephone=").append(telephone);
        builder.append(", role=").append(role);
        builder.append(", status=").append(status);
        builder.append("}");
        return builder.toString();
    }

    /**
     * The type Builder.
     */
    public static class Builder {
        private User newUser;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            newUser = new User();
        }

        /**
         * Sets user id.
         *
         * @param userId the user id
         * @return the user id
         */
        public Builder setUserId(long userId) {
            newUser.userId = userId;
            return this;
        }

        /**
         * Sets login.
         *
         * @param login the login
         * @return the login
         */
        public Builder setLogin(String login) {
            newUser.login = login;
            return this;
        }

        /**
         * Sets password.
         *
         * @param password the password
         * @return the password
         */
        public Builder setPassword(String password) {
            newUser.password = password;
            return this;
        }


        /**
         * Sets first name.
         *
         * @param firstName the first name
         * @return the first name
         */
        public Builder setFirstName(String firstName) {
            newUser.firstName = firstName;
            return this;
        }

        /**
         * Sets last name.
         *
         * @param lastName the last name
         * @return the last name
         */
        public Builder setLastName(String lastName) {
            newUser.lastName = lastName;
            return this;
        }

        /**
         * Sets email.
         *
         * @param email the email
         * @return the email
         */
        public Builder setEmail(String email) {
            newUser.email = email;
            return this;
        }

        /**
         * Sets telephone.
         *
         * @param telephone the telephone
         * @return the telephone
         */
        public Builder setTelephone(String telephone) {
            newUser.telephone = telephone;
            return this;
        }

        /**
         * Sets status.
         *
         * @param status the status
         * @return the status
         */
        public Builder setStatus(Status status) {
            newUser.status = status;
            return this;
        }

        /**
         * Sets role.
         *
         * @param role the role
         * @return the role
         */
        public Builder setRole(Role role) {
            newUser.role = role;
            return this;
        }

        /**
         * Build user.
         *
         * @return the user
         */
        public User build() {
            return newUser;
        }
    }
}
