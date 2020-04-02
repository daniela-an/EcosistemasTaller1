package appmoviles.com.tcpclienteavanzado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ComunicacionTCP comm;

    private Button up, left, right, down, shoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String ip = "10.0.2.2";
        comm = new ComunicacionTCP(this);
        comm.solicitarConexion( ip );

        up = findViewById(R.id.up);
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        down = findViewById(R.id.down);
        shoot = findViewById(R.id.shoot);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comm.mandarMensaje("up");
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comm.mandarMensaje("left");
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comm.mandarMensaje("right");
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comm.mandarMensaje("down");
            }
        });

        shoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comm.mandarMensaje("shoot");
            }
        });
    }
}
