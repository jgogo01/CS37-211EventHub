package cs211.project.controllers.components;

import cs211.project.models.TeamMember;
import cs211.project.models.collections.TeamCollection;
import cs211.project.models.collections.TeamMemberCollection;
import cs211.project.services.Datasource;
import cs211.project.services.TeamMemberListFileDatasource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.lang.String;
import java.util.HashMap;
import java.util.UUID;

public class JoinTeamCard {

    private UUID teamId;
    private UUID userId;
    @FXML
    private Button joinTeamButton;

    @FXML
    private Label teamParticipant;

    @FXML
    private Label teamName;

    @FXML
    private Button teamOrder;
    private HashMap<String, Object> data;

    private Datasource<TeamMemberCollection> teamMemberDatasource;

    public JoinTeamCard() {
        this.teamParticipant = new Label();
        this.teamName = new Label();
        this.teamOrder = new Button();
    }
    @FXML
    private void initialize() {
        teamMemberDatasource = new TeamMemberListFileDatasource("data/team", "teamMember.csv");
    }

    @FXML
    void onHandleJoinTeam(ActionEvent event) {
        TeamMemberCollection newMember = new TeamMemberCollection();
        TeamMember teamMember = new TeamMember(UUID.randomUUID().toString(),userId.toString(),teamId.toString());
        newMember.addTeamMember(teamMember);
        teamMemberDatasource.writeData(newMember);
        isJoinedTeam();
    }

    public void isJoinedTeam(){
        joinTeamButton.setDisable(true);
        joinTeamButton.setText("เข้าร่วมแล้ว");
    }
    public String getTeamParticipant() { return teamParticipant.getText(); }

    public String getTeamName() { return teamName.getText(); }

    public String getTeamOrder() { return teamOrder.getText(); }

    public String getTeamId() {
        return teamId.toString();
    }

    public String getUserId() {
        return userId.toString();
    }

    public void setTeamParticipant(String teamParticipant) { this.teamParticipant.setText(teamParticipant); }

    public void setTeamName(String teamName) { this.teamName.setText(teamName); }

    public void setTeamOrder(String teamOrder) { this.teamOrder.setText(teamOrder); }

    public void setTeamId(String teamId) {
        this.teamId = UUID.fromString(teamId);
    }

    public void setUserId(String userId) {
        this.userId = UUID.fromString(userId);
    }
}