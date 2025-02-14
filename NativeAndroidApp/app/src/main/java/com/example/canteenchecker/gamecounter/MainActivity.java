package com.example.canteenchecker.gamecounter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PlayerAdapter playerAdapter = new PlayerAdapter();
    private RecyclerView playerView;
    private static List<Player> playerList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerView = findViewById(R.id.playerView);
        playerView.setLayoutManager(new LinearLayoutManager(this));
        playerView.setAdapter(playerAdapter);
    }

    public void addPlayer(View view) {
        playerList.add(new Player(""));
        playerAdapter.notifyItemInserted(playerList.size() - 1);
    }

    private static class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {

        @NonNull
        @Override
        public PlayerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PlayerAdapter.ViewHolder holder, int position) {
            holder.player = playerList.get(position);
            holder.updateView();
        }

        @Override
        public int getItemCount() {
            return playerList.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {

            private final EditText nameField = itemView.findViewById(R.id.nameField);
            private final TextView scoreField = itemView.findViewById(R.id.scoreField);
            private Player player;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                EditText text = itemView.findViewById(R.id.editScoreField);

                itemView.findViewById(R.id.updateButton).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            if (!text.getText().toString().equals("")) {
                                player.setScore(player.getScore() + Integer.parseInt(text.getText().toString()));
                                text.setText("");
                                updateView();
                            }
                        } catch (Exception e) {
                            text.setText("");
                        }
                    }
                });

                TextView nameField = itemView.findViewById(R.id.nameField);
                nameField.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        player.setName(nameField.getText().toString());
                    }
                });
            }

            void updateView() {
                nameField.setText(player.getName());
                scoreField.setText(String.valueOf(player.getScore()));
            }
        }
    }
}