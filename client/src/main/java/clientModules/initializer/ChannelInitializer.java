package clientModules.initializer;

import java.io.IOException;
import java.nio.channels.DatagramChannel;

public class ChannelInitializer implements InitializeChannelAble {

    @Override
    public DatagramChannel init() {
        try {
            DatagramChannel datagramChannel = DatagramChannel.open();
            // datagramChannel.configureBlocking(false);
            // (!)
            // По требованиям лабы нужно использовать этот метод, но это бред. С этим методом вся логика запросов-ответов
            // рушится. Мы отправляем запрос, чтобы получить ответ. Чтобы получить ответ, нужно подождать. Отправку запросов
            // и получение ответов банально можно оставить на другой поток. Данный же метод позволяет не ждать => в стандартной
            // логике ловим кучу ошибок и ничего не работает. Логика с этим методом и без ошибок будет примерной такой: отправляем
            // что-то на сервер одним потоком, другим потоком делаем бесконечный цикл с методом receive и проверкой ifReceived.
            // Если что-то пришло - работаем, если нет - цикл продолжается.
            // (!)
            return datagramChannel;
        } catch (IOException e) {
            return null;
        }
    }
}
