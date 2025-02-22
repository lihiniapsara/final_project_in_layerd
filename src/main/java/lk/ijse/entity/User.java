package lk.ijse.entity;

public class User {
        private String userId;
        private String userName;
        private String password;
        private String contact;

        public User() {
        }

        public User(String userId, String userName, String password, String contact) {
                this.userId = userId;
                this.userName = userName;
                this.password = password;
                this.contact = contact;
        }

        public String getUserId() {
                return userId;
        }

        public void setUserId(String userId) {
                this.userId = userId;
        }

        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }


        public String getContact() {
                return contact;
        }

        public void setContact(String contact) {
                this.contact = contact;
        }

        @Override
        public String toString() {
                return "User{" +
                        "userId='" + userId + '\'' +
                        ", userName='" + userName + '\'' +
                        ", password='" + password + '\'' +
                        ", contact='" + contact + '\'' +
                        '}';
        }
}
