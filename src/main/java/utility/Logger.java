package utility;

public class Logger {
    private StringBuilder builder = new StringBuilder();
    private String tag = "";

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Logger(Class className) {
        builder.append(className.getName());
    }

    public void d(String message) {
        d(tag, message);
    }

    public void d(String tag, String message) {
        System.out.println("d/"+builder.toString() + " " + tag + ":" + message);
    }
    
}
