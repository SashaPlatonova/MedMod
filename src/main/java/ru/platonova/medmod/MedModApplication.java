package ru.platonova.medmod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MedModApplication {


    // TODO: 20.03.2022 удаление записи из расписания + модалка подтверждения
    // TODO: 20.03.2022 редактирования расписания ???
    // TODO: 20.03.2022 сообщения об ошибках + уведомления об успешности
    // TODO: 20.03.2022 изменение пароля с подтверждением
    public static void main(String[] args) {
        SpringApplication.run(MedModApplication.class, args);
    }

}
