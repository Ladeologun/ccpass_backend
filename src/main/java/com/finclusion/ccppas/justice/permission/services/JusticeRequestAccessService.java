package com.finclusion.ccppas.justice.permission.services;

import com.finclusion.ccppas.helper.CustomRuntimeException;
import com.finclusion.ccppas.justice.permission.dtos.GrantAccessRequestDto;
import com.finclusion.ccppas.justice.permission.dtos.GrantAccessResponseDto;
import com.finclusion.ccppas.justice.permission.dtos.RequestAccessDto;
import com.finclusion.ccppas.justice.permission.dtos.RequestAccessResponseDto;
import com.finclusion.ccppas.justice.permission.models.JusticePermission;
import com.finclusion.ccppas.justice.permission.models.JusticePermissionStatus;
import com.finclusion.ccppas.justice.permission.models.JusticePermissionType;
import com.finclusion.ccppas.justice.permission.repositories.JusticePermissionRepository;
import com.finclusion.ccppas.user.models.UserStatus;
import com.finclusion.ccppas.user.repositories.JusticePractitionerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
@RequiredArgsConstructor
public class JusticeRequestAccessService {

    private final JusticePractitionerRepository justicePractitionerRepository;
    private final JusticePermissionRepository justicePermissionRepository;

    public RequestAccessResponseDto justiceRequestAccess(RequestAccessDto request)  {
        //check if the fmoj and email belong to same person
        var possibleJustice = justicePractitionerRepository.findByUniqueId(request.getUniqueId());
        var justice = possibleJustice.get();
        var errors = new HashMap<String,String>();
        var existingJusticeEmail = justice.getEmail().toLowerCase();
        var requestEmail = request.getEmail().toLowerCase();

        if (!existingJusticeEmail.equals(requestEmail)){
            errors.put("email", "email does not match our record");
        }

        if (justice.getBvn() != null && !(justice.getBvn().equals(request.getBvn()))){
            errors.put("bvn", "bvn does not match our record");
        }

        if (justice.getNin() != null && !(justice.getNin().equals(request.getNin()))){
            errors.put("nin", "nin does not match our record");
        }

        var possibleSupervisor = justicePractitionerRepository.findByEmail(request.getSupervisorEmail());
        var supervisor = possibleSupervisor.get();
        if (!justice.getSupervisor().equals(supervisor)){
            errors.put("supervisorEmail", "supervisor not assigned");
        }

        if (!errors.isEmpty()){
            throw new CustomRuntimeException(errors,"Error during validation");
        }

        var permission  = JusticePermission.builder()
                        .type(JusticePermissionType.ACCESS_PLATFORM)
                        .status(JusticePermissionStatus.PENDING)
                        .sender(justice)
                        .receiver(supervisor)
                        .build();
        var savePermission = justicePermissionRepository.save(permission);

        return RequestAccessResponseDto.builder()
                .id(savePermission.getId())
                .type(savePermission.getType())
                .senderEmail(savePermission.getSender().getEmail())
                .senderFullname(savePermission.getSender().fullName())
                .supervisorEmail(savePermission.getReceiver().getEmail())
                .build();
    }


    public GrantAccessResponseDto justiceGrantAccess(GrantAccessRequestDto request){

         var possiblePermission = justicePermissionRepository.findById(request.getId());
         if (possiblePermission.isEmpty()){
             throw new CustomRuntimeException(null, "permission request not found");
         };

         var permission = possiblePermission.get();
         var sender = permission.getSender();
         sender.setStatus(UserStatus.ACTIVE);
         justicePractitionerRepository.save(sender);
         permission.setStatus(JusticePermissionStatus.APPROVED);
         var savedPermission = justicePermissionRepository.save(permission);

         return GrantAccessResponseDto.builder()
                 .id(savedPermission.getId())
                 .status(savedPermission.getStatus())
                 .build();
    }


}
