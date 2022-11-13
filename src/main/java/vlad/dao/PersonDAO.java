package vlad.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import vlad.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person1", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int person_id) {
        return jdbcTemplate.query("SELECT * FROM Person1 WHERE person_id=?", new Object[]{person_id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
    public Optional<Person> show(String fio) {
        return jdbcTemplate.query("SELECT * FROM Person1 WHERE fio=?", new Object[]{fio}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person1(fio,year_of_birth) VALUES (?,?)", person.getFio(), person.getYear_of_birth());
    }

    public void update(int person_id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person1 SET fio=?, year_of_birth=? WHERE person_id=?", updatedPerson.getFio(),
                updatedPerson.getYear_of_birth(), person_id);

    }

    public void delete(int person_id) {
        jdbcTemplate.update("DELETE FROM Person1 WHERE person_id=?", person_id);

    }
}
