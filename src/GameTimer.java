import java.time.Instant;
import java.util.TimerTask;

public class GameTimer extends TimerTask {

    private Controller controller;
    public GameTimer(Controller controller){
        this.controller = controller;
    }
    @Override
    public void run() {
        Instant currtime = Instant.now();
        controller.updateTime(currtime);
    }
}
