package Controller;

import com.banquemisr.moneytransactionservice.dto.CreateUserDTO;
import com.banquemisr.moneytransactionservice.exception.custom.UserAlreadyExistsException;
import com.banquemisr.moneytransactionservice.service.IAuthenticator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestAuthenticatotController {


//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private IAuthenticator authenticator;
//
//
//    @Test
//    void testRegisterUserWithInvalidRequestBody() throws Exception {
//
//        mockMvc.perform(post("/api/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\r\n    \"username\": \"momo1\",\r\n    \"password\": \"123456\",\r\n    \"email\": \"momo1@gmail.com\",\r\n    \"birthdate\": \"2000-3-3\",\r\n    \"phone\": \"01111884451\",\r\n    \"gender\": \"MALE\",\r\n    \"type\": \"no\"}"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void testRegisterUserWithValidRequestBody() throws Exception {
//        mockMvc.perform(post("/api/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\r\n    \"username\": \"momo1\",\r\n    \"password\": \"123456\",\r\n    \"email\": \"momo1@gmail.com\",\r\n    \"birthdate\": \"2000-3-3\",\r\n    \"phone\": \"01111884451\",\r\n    \"gender\": \"MALE\"}"))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void testRegisterWithAlreadyExistingUser() throws Exception {
//
//        Mockito.when(this.authenticator.register(any(CreateUserDTO.class)))
//                .thenThrow(new UserAlreadyExistsException("User already exists"));
//
//        mockMvc.perform(post("/api/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\r\n    \"username\": \"momo1\",\r\n    \"password\": \"123456\",\r\n    \"email\": \"momo1@gmail.com\",\r\n    \"birthdate\": \"2000-3-3\",\r\n    \"phone\": \"01111884451\",\r\n    \"gender\": \"MALE\"}"))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void testAuthenticate() throws Exception {
//        mockMvc.perform(post("/api/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\r\n    \"username\": \"momo1\",\"email\":\"momo1@gmail.com\", \"password\":\"123456\"}"))
//                .andExpect(status().isOk());
//    }
}
