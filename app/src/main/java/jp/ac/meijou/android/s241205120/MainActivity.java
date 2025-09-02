package jp.ac.meijou.android.s241205120;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import jp.ac.meijou.android.s241205120.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); // ← 括弧を忘れない＆二重呼び出ししない

        prefDataStore = PrefDataStore.getInstance(this);

        // Changeボタン：入力→テキストへ反映
        binding.button.setOnClickListener(view -> {
            var text = binding.editTextText.getText().toString(); // toString()
            binding.text.setText(text);
        });

        // Saveボタン：DataStoreへ保存
        binding.saveButton.setOnClickListener(view -> {
            var text = binding.editTextText.getText().toString();
            prefDataStore.setString("name", text);
        });

        // 入力が変わったら即時反映（演習7の発展）
        binding.editTextText.addTextChangedListener(new android.text.TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void afterTextChanged(android.text.Editable e) {
                binding.text.setText(e.toString());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 起動時に保存済みを表示（演習8＋ライフサイクル）
        prefDataStore.getString("name")
                .ifPresent(name -> binding.text.setText(name));
    }
}
