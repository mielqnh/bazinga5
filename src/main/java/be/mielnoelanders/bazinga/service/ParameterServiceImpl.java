package be.mielnoelanders.bazinga.service;

import be.mielnoelanders.bazinga.domain.Parameter;
import be.mielnoelanders.bazinga.domain.ParameterEnum;
import be.mielnoelanders.bazinga.repository.ParameterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Arrays;

@Service
@Transactional
public class ParameterServiceImpl implements ParameterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterService.class);

    @Autowired
    private ParameterRepository repo;

    @PostConstruct
    public void init(){

        Parameter parm1 = new Parameter();
            parm1.setType(ParameterEnum.PROFITMARGIN);
            parm1.setPercentage(30);
        Parameter parm2 = new Parameter();
            parm2.setType(ParameterEnum.PREMIUMCUSTOMER);
            parm2.setPercentage(10);
        Parameter parm3 = new Parameter();
            parm3.setType(ParameterEnum.DAMAGEDISCOUNT);
            parm3.setPercentage(20);

        this.repo.saveAll(Arrays.asList(parm1,parm2,parm3));
    }

    @Override
    public Parameter addParameter(Parameter parameter) {
        return repo.save(parameter);
    }

    @Override
    public Iterable<Parameter> getAll() {
        return this.repo.findAll();
    }

    @Override
    public Parameter findByType(ParameterEnum type) {
        return repo.findByType(type);
    }

    @Override
    public boolean updateParameterByType(Parameter parameter) {
        Parameter result = (Parameter) repo.findByType(parameter.getType());

        if (result != null) {
            result.setType(parameter.getType());
            result.setPercentage(parameter.getPercentage());

            this.repo.save(result);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteParameter(Long id) {
        if (this.repo.existsById(id)) {
            this.repo.deleteById(id);
            return true;
        }
        return false;
    }
}