package cs211.project.controllers;

import cs211.project.controllers.components.MyEventCard;
import cs211.project.models.Event;
import cs211.project.models.collections.EventCollection;
import cs211.project.models.collections.JoinEventCollection;
import cs211.project.services.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.HashMap;

public class MyEventController {
    @FXML
    private AnchorPane navbar;
    @FXML
    private AnchorPane footer;
    @FXML
    private VBox myEventComponent;
    private Datasource<EventCollection> datasource;
    private Datasource<JoinEventCollection> datasourceJoinEvent;
    private EventCollection myEventList;
    private HashMap<String, Object> data;

    @FXML
    private void initialize() {
        data = FXRouter.getData();
        data.remove("eventId");
        //Datasource
        datasource = new EventListFileDatasource("data/event", "event.csv");
        myEventList = datasource.query("userId = " + data.get("userId"));
        //Filter JoinEvent by eventId
        datasourceJoinEvent = new JoinEventListFileDatasource("data/event", "joinEvent.csv");

        //Navbar
        FXMLLoader navbarComponentLoader = new FXMLLoader(getClass().getResource("/cs211/project/views/navbar.fxml"));
        //Footer
        FXMLLoader footerComponentLoader = new FXMLLoader(getClass().getResource("/cs211/project/views/footer.fxml"));
        try {
            //Navbar
            AnchorPane navbarComponent = navbarComponentLoader.load();
            NavbarController navbarController = navbarComponentLoader.getController();
            navbarController.setData(data);
            navbar.getChildren().add(navbarComponent);

            //Footer
            AnchorPane footerComponent = footerComponentLoader.load();
            footer.getChildren().add(footerComponent);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Component
        int i = 0;
        for (Event myEventCardData : myEventList.getEvents()) {
            //Reset
            try {
                FXMLLoader myEventCardControllerLoader = new FXMLLoader();
                myEventCardControllerLoader.setLocation(getClass().getResource("/cs211/project/views/components/my-event-card.fxml"));
                AnchorPane myEventCardComponent = myEventCardControllerLoader.load();
                MyEventCard myEventCard = myEventCardControllerLoader.getController();
                //Set Value in List
                myEventCard.setEventTitle(myEventCardData.getName());
                myEventCard.setEventDate(myEventCardData.getStartDate());
                myEventCard.setEventImage(myEventCardData.getImage());
                myEventCard.setEventLocation(myEventCardData.getLocation());
                myEventCard.setMyEventController(this);
                myEventCard.setStatusEvent(myEventCardData.isStatus());
                //Set Data
                HashMap<String, Object> dataEventComponent = new HashMap<>();
                dataEventComponent.put("eventId", myEventCardData.getId());
                dataEventComponent.put("userId", myEventCardData.getUserId());
                myEventCard.setData(dataEventComponent);

                //Filter JoinEvent by eventId
                JoinEventCollection joinEventList = datasourceJoinEvent.query("eventId = " + myEventCardData.getId());
                myEventCard.setParticipantEvent(joinEventList.getJoinEvents().size() + "/" + myEventCardData.getMaxParticipant());

                myEventCard.setOrderNumber(String.valueOf(++i));
                //Insert to Component
                myEventComponent.getChildren().add(myEventCardComponent);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @FXML
    public void goToCreateEvent() {
        try {
            FXRouter.goTo("createEvent", data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void reload() {
        myEventComponent.getChildren().clear();
        initialize();
    }
}
