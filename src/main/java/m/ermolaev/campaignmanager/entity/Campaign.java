package m.ermolaev.campaignmanager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campaign name is mandatory")
    private String name;

    @NotBlank(message = "Keywords are mandatory")
    private String keywords;

    @NotNull(message = "Bid amount is mandatory")
    @DecimalMin(value = "0.01", message = "Bid amount must be at least 0.01")
    private BigDecimal bidAmount;

    @NotNull(message = "Campaign fund is mandatory")
    @DecimalMin(value = "0.01", message = "Campaign fund must be at least 0.01")
    private BigDecimal campaignFund;

    @NotNull(message = "Status is mandatory")
    private Boolean status;

    @NotBlank(message = "Town is mandatory")
    private String town;

    @NotNull(message = "Radius is mandatory")
    @Min(value = 1, message = "Radius must be at least 1 km")
    private Integer radius;
}