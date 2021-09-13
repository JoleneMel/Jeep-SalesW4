package com.promineotech.jeep.controller;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
//This is the correct RequestBody
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.promineotech.jeep.entity.Order;
import com.promineotech.jeep.entity.OrderRequest;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
//Be aware that the below is the wrong import
//import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/orders")
@OpenAPIDefinition(info = @Info(title = "Jeep Order Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface JeepOrderController {


  // @formatter:off
  @Operation(
      summary = "Create an Order for a Jeep",
      description = "Returns the created Jeep",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "A created Jeep is returned",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Order.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "A Jeeps component was not found with the input criteria",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(name = "orderRequest", 
              required = true, 
              description = "The order as JSON")
          }
  )
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Order createOrder(@Valid @RequestBody OrderRequest orderRequest);
  //@formatter:on
}