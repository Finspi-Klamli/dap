package belov.vlad.dapp.services;

import belov.vlad.dapp.model.Equipment;
import belov.vlad.dapp.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService{
    @Autowired
    EquipmentRepository equipmentRepository;

    @Override
    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }
}
