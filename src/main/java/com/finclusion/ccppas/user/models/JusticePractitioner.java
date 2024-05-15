package com.finclusion.ccppas.user.models;

import com.finclusion.ccppas.crimecase.models.CrimeCase;
import com.finclusion.ccppas.justice.actionlog.JusticeActionLog;
import com.finclusion.ccppas.justice.chat.Chat;
import com.finclusion.ccppas.justice.chat.Message;
import com.finclusion.ccppas.justice.permission.JusticePermission;
import com.finclusion.ccppas.justice.task.JusticeTask;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@SuperBuilder
@NoArgsConstructor
public class JusticePractitioner extends BasicUser {

    @ManyToOne
    @JoinColumn(name = "role_id")
    private JusticePractitionerRole role;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private JusticePractitionerType type;

    @OneToMany(mappedBy = "sender")
    private List<JusticePermission> sentPermissions;

    @OneToMany(mappedBy = "receiver")
    private List<JusticePermission> receivedPermissions;

    @OneToMany(mappedBy = "user")
    private List<JusticeActionLog> actionLogs;

    @OneToMany(mappedBy = "user")
    private List<JusticeTask> tasks;

    @OneToMany(mappedBy = "judge")
    private List<CrimeCase> judgedCases;
    @OneToMany(mappedBy = "sender")
    private List<Message> sentMessages;
    @OneToMany(mappedBy = "receiver")
    private List<Message> receivedMessages;

    @ManyToMany(mappedBy = "participants")
    private List<Chat> chats;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private JusticePractitioner supervisor;

    @OneToMany(mappedBy = "supervisor")
    private List<JusticePractitioner> subordinates;


    public JusticePractitioner(String unique_id, String firstname, String lastname, String email) {
        super(unique_id, firstname, lastname, email);
    }
}
