package jp.ac.meijou.android.s241205120;

import android.os.Bundle;

import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s241205173.PrefDataStore;
import jp.ac.meijou.android.s241205173.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        TextView text = findViewById(R.id.text);
        binding.button.setOnClickListener(view -> {
            var textaf = binding.editTextText.getText().toString();
            binding.text.setText(textaf);
        });

        prefDataStore = PrefDataStore.getInstance(this);
        prefDataStore.getString("name").ifPresent(name -> binding.text.setText(name));
        binding.saveButton.setOnClickListener(view -> {
            var text1 = binding.editTextText.getText().toString();
            prefDataStore.setString("name",text1);
        });
    }







    protected void onStart() {
        super.onStart();

        prefDataStore.getString("name")
                .ifPresent(name -> binding.text.setText(name));
    }
}