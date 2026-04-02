package senai.centroweg.weg_workshop_api.infrastructure.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import senai.centroweg.weg_workshop_api.application.dto.request.ServiceOrderApproveRequestDTO;
import senai.centroweg.weg_workshop_api.application.dto.request.ServiceOrderExecuteRequestDTO;
import senai.centroweg.weg_workshop_api.application.dto.request.ServiceOrderRequestDTO;
import senai.centroweg.weg_workshop_api.application.dto.response.ServiceOrderResponseDTO;
import senai.centroweg.weg_workshop_api.application.service.ServiceOrderService;

@RestController
@RequestMapping("/api/service-orders")
public class ServiceOrderController {

    private final ServiceOrderService serviceOrderService;

    public ServiceOrderController(ServiceOrderService serviceOrderService) {
        this.serviceOrderService = serviceOrderService;
    }

    @PostMapping
    public ResponseEntity<ServiceOrderResponseDTO> openServiceOrder(@RequestBody @Valid ServiceOrderRequestDTO requestDTO) {
        ServiceOrderResponseDTO response = serviceOrderService.openServiceOrder(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/execute")
    public ResponseEntity<ServiceOrderResponseDTO> executeServiceOrder(@RequestBody @Valid ServiceOrderExecuteRequestDTO requestDTO) {
        ServiceOrderResponseDTO response = serviceOrderService.executeServiceOrder(requestDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/approve")
    public ResponseEntity<ServiceOrderResponseDTO> approveServiceOrder(@RequestBody @Valid ServiceOrderApproveRequestDTO requestDTO) {
        ServiceOrderResponseDTO response = serviceOrderService.approveServiceOrder(requestDTO);
        return ResponseEntity.ok(response);
    }
}