package iprint.uajy.com.iprint;

public class UserDAO {
        String name;
        String email;
        String password;
        String address;
        String phone;


        public UserDAO(String name, String email, String password, String address, String phone) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.address = address;
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public String getEmail() { return email; }

        public String getPassword() {
            return password;
        }

        public String getAddress() {
            return address;
        }

        public String getPhone() {
        return phone;
    }



        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) { this.email = email; }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setPhone(String phone) {
        this.phone = phone;
    }
}
