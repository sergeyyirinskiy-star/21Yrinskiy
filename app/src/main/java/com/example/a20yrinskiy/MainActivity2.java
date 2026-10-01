package com.example.a20yrinskiy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    ImageView playerCar;
    ImageView enemyCar;

    Button leftButton;
    Button rightButton;
    Button startButton;

    View finishLine;

    boolean started = false;
    boolean finished = false;

    float playerPosition = 0;
    float enemyPosition = 0;

    float finishPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        // Находим элементы
        playerCar = findViewById(R.id.playerCar);
        enemyCar = findViewById(R.id.enemyCar);

        leftButton = findViewById(R.id.leftButton);
        rightButton = findViewById(R.id.rightButton);
        startButton = findViewById(R.id.startButton);

        finishLine = findViewById(R.id.finishLine);

        // Запоминаем положение финиша
        finishLine.post(() -> {

            finishPosition =
                    finishLine.getLeft() - playerCar.getLeft()
                            - playerCar.getWidth() - 10;

        });

        // Кнопка СТАРТ
        startButton.setOnClickListener(v -> startGame());

        // Игрок 1
        leftButton.setOnClickListener(v -> movePlayer());

        // Игрок 2
        rightButton.setOnClickListener(v -> moveEnemy());
    }

    // Запуск игры
    private void startGame() {

        if (!started || finished) {

            started = true;
            finished = false;

            playerPosition = 0;
            enemyPosition = 0;

            playerCar.setTranslationX(0);
            enemyCar.setTranslationX(0);

            startButton.setText("ПАУЗА");

            Toast.makeText(
                    this,
                    "Гонка началась!",
                    Toast.LENGTH_SHORT
            ).show();

        } else {

            started = false;

            startButton.setText("СТАРТ");

            Toast.makeText(
                    this,
                    "Гонка остановлена",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    // Движение машины первого игрока
    private void movePlayer() {

        if (!started || finished) {
            return;
        }

        playerPosition += 25;

        playerCar.setTranslationX(playerPosition);

        checkFinish();
    }

    // Движение машины второго игрока
    private void moveEnemy() {

        if (!started || finished) {
            return;
        }

        enemyPosition += 25;

        enemyCar.setTranslationX(enemyPosition);

        checkFinish();
    }

    // Проверяем, кто достиг финиша
    private void checkFinish() {

        if (playerPosition >= finishPosition) {

            finished = true;
            started = false;

            startButton.setText("СТАРТ");

            Toast.makeText(
                    this,
                    " ПОБЕДИЛ ИГРОК 1!",
                    Toast.LENGTH_LONG
            ).show();

        } else if (enemyPosition >= finishPosition) {

            finished = true;
            started = false;

            startButton.setText("СТАРТ");

            Toast.makeText(
                    this,
                    " ПОБЕДИЛ ИГРОК 2!",
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}