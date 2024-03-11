package than.MK.weblaptop.backend.service;

public interface EmailServiceInterface {
    public void sendMessage(String from, String to, String subject, String text);
}
