package dailydescretedeck.set.viewmodels;

import dailydescretedeck.set.models.Player;

public class ProfileViewModel {
    private Player player;
    private CalendarViewModel calendarViewModel;

    public ProfileViewModel() {
        this.calendarViewModel = new CalendarViewModel();
    }

    public Player getPlayer() {
        return player;
    }

    public CalendarViewModel getCalendarViewModel() {
        return calendarViewModel;
    }
}
