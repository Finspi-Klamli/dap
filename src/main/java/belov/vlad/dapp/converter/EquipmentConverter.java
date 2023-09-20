package belov.vlad.dapp.converter;

import belov.vlad.dapp.model.Equipment;
import belov.vlad.dapp.services.EquipmentService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EquipmentConverter implements Converter<String, Equipment> {
    private final EquipmentService equipmentService;
    public EquipmentConverter(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }
    @Override
    public Equipment convert(String s) {
        return equipmentService.findForConversion(s);
    }
}
