package com.epam.ld.module2.model;

import java.util.Map;

/**
 * The type Client.
 */
public final class Client {

    private final String email;

    private final String password;

    private final String targetEmail;

    private final String subject;

    private final Map<String, String> tags;

    Client(String email, String password, String targetEmail, String subject, Map<String, String> tags) {
        this.email = email;
        this.password = password;
        this.targetEmail = targetEmail;
        this.subject = subject;
        this.tags = tags;
    }

    public static ClientBuilder builder() {
        return new ClientBuilder();
    }


    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getTargetEmail() {
        return this.targetEmail;
    }

    public String getSubject() {
        return this.subject;
    }

    public Map<String, String> getTags() {
        return this.tags;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Client)) return false;
        final Client other = (Client) o;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$targetEmail = this.getTargetEmail();
        final Object other$targetEmail = other.getTargetEmail();
        if (this$targetEmail == null ? other$targetEmail != null : !this$targetEmail.equals(other$targetEmail))
            return false;
        final Object this$subject = this.getSubject();
        final Object other$subject = other.getSubject();
        if (this$subject == null ? other$subject != null : !this$subject.equals(other$subject)) return false;
        final Object this$tags = this.getTags();
        final Object other$tags = other.getTags();
        if (this$tags == null ? other$tags != null : !this$tags.equals(other$tags)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $targetEmail = this.getTargetEmail();
        result = result * PRIME + ($targetEmail == null ? 43 : $targetEmail.hashCode());
        final Object $subject = this.getSubject();
        result = result * PRIME + ($subject == null ? 43 : $subject.hashCode());
        final Object $tags = this.getTags();
        result = result * PRIME + ($tags == null ? 43 : $tags.hashCode());
        return result;
    }

    public String toString() {
        return "Client(email=" + this.getEmail() + ", password=" + this.getPassword() + ", targetEmail=" + this.getTargetEmail() + ", subject=" + this.getSubject() + ", tags=" + this.getTags() + ")";
    }

    public Client withEmail(String email) {
        return this.email == email ? this : new Client(email, this.password, this.targetEmail, this.subject, this.tags);
    }

    public Client withPassword(String password) {
        return this.password == password ? this : new Client(this.email, password, this.targetEmail, this.subject, this.tags);
    }

    public Client withTargetEmail(String targetEmail) {
        return this.targetEmail == targetEmail ? this : new Client(this.email, this.password, targetEmail, this.subject, this.tags);
    }

    public Client withSubject(String subject) {
        return this.subject == subject ? this : new Client(this.email, this.password, this.targetEmail, subject, this.tags);
    }

    public Client withTags(Map<String, String> tags) {
        return this.tags == tags ? this : new Client(this.email, this.password, this.targetEmail, this.subject, tags);
    }

    public static class ClientBuilder {
        private String email;
        private String password;
        private String targetEmail;
        private String subject;
        private Map<String, String> tags;

        ClientBuilder() {
        }

        public ClientBuilder email(String email) {
            this.email = email;
            return this;
        }

        public ClientBuilder password(String password) {
            this.password = password;
            return this;
        }

        public ClientBuilder targetEmail(String targetEmail) {
            this.targetEmail = targetEmail;
            return this;
        }

        public ClientBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public ClientBuilder tags(Map<String, String> tags) {
            this.tags = tags;
            return this;
        }

        public Client build() {
            return new Client(this.email, this.password, this.targetEmail, this.subject, this.tags);
        }

        public String toString() {
            return "Client.ClientBuilder(email=" + this.email + ", password=" + this.password + ", targetEmail=" + this.targetEmail + ", subject=" + this.subject + ", tags=" + this.tags + ")";
        }
    }
}
