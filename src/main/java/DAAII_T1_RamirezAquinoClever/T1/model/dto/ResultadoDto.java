package DAAII_T1_RamirezAquinoClever.T1.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResultadoDto {
    private Boolean respuesta;
    private String mensaje;
}