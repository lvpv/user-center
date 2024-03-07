package com.lvpb.controller;


import com.lvpb.convert.UserConvert;
import com.lvpb.model.domain.User;
import com.lvpb.model.request.LoginRequest;
import com.lvpb.model.request.RegisterRequest;
import com.lvpb.model.response.UserResponse;
import com.lvpb.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Tag(name = "认证管理")
@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {


    @Resource
    private AuthService authService;


    @PostMapping("/login")
    @Operation(summary = "用户登陆")
    // @Parameter(name = "files",description = "文件",required = true,in = ParameterIn.QUERY,ref = "MultipartFile")
    public UserResponse authLogin(@Valid @RequestBody LoginRequest loginRequest, HttpSession session) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        User user = authService.authLogin(username, password, session);
        return UserConvert.INSTANCE.convertResponse(user);
    }

    @PostMapping("/register")
    @Operation(summary = "用户注册")
    public Long authRegister(@Valid @RequestBody RegisterRequest registerRequest) {
        String username = registerRequest.getUsername();
        String password = registerRequest.getPassword();
        String checkPassword = registerRequest.getCheckPassword();
        return authService.authRegister(username, password, checkPassword);
    }

    @GetMapping("/info")
    @Operation(summary = "获取当前登录用户信息")
    public UserResponse getLoginInfo(HttpSession session){
        User user = authService.getLoginInfo(session);
        return UserConvert.INSTANCE.convertResponse(user);
    }

    /*@Operation(summary = "附件上传",description = "附近上传-xxx",requestBody = @RequestBody(content = {
            @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,schema = @Schema(type = "object"),
                    schemaProperties = {
                            @SchemaProperty(name = "file",array = @ArraySchema(schema = @Schema(type = "string",format = "binary",requiredMode = Schema.RequiredMode.REQUIRED))),
                            @SchemaProperty(name = "module",schema = @Schema(type = "string",required = true)),
                            @SchemaProperty(name = "bizNo",schema = @Schema(type = "string",required = true)),
                            @SchemaProperty(name = "bizType",schema = @Schema(type = "integer",required = true))
                    })
    }))
    @PostMapping(value = "/module/upload1",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fileModule1(FileRequestVo fileRequestVo){
        return ResponseEntity.ok(RandomUtil.randomString(23));
    }*/
}
