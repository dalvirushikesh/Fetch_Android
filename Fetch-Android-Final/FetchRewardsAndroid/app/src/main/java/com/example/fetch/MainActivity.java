package com.example.fetch;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private static final String URL = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
    private JSONArray jsonArray;
    private Button fetchDataButton;
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchDataButton = findViewById(R.id.fetchDataButton);
        tableLayout = findViewById(R.id.table);

        // button click listener to fetch and display data
        fetchDataButton.setOnClickListener(view -> {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(() -> {
                try {
                    // Fetch JSON data from the URL
                    jsonArray = FetchData.fetchJsonFromUrl(URL);
                } catch (JSONException | IOException e) {
                    throw new RuntimeException(e);
                }

                // Update the UI with the fetched data
                runOnUiThread(() -> {
                    Map<Integer, List<Item>> items = new HashMap<>();

                    if (jsonArray.length() > 0) {
                        try {
                            // Parse JSON and organize items by listId
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String itemName = object.getString("name");
                                if (!itemName.isEmpty() && !"null".equals(itemName)) {
                                    Item item = new Item(object.getInt("id"), object.getInt("listId"), itemName);
                                    items.computeIfAbsent(item.getListId(), k -> new ArrayList<>()).add(item);
                                }
                            }

                            // Sort items within each listId by the integer value in their name
                            items.values().forEach(itemList ->
                                    itemList.sort(Comparator.comparing(item -> extractIntegerValue(item.getName())))
                            );

                            // Create table headers
                            createTableHeaders();

                            // Populate table with sorted items
                            items.entrySet().stream()
                                    .sorted(Map.Entry.comparingByKey())
                                    .forEach(entry -> {
                                        int listId = entry.getKey();
                                        List<Item> itemList = entry.getValue();

                                        // Create a row for each item
                                        itemList.forEach(item -> createTableRows(listId, item.getId(), item.getName()));
                                    });

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    // Disable the button after fetching data
                    fetchDataButton.setEnabled(false);
                });
            });
        });
    }

    // Create a row in the table for each item
    private void createTableRows(int listId, int id, String name) {
        TableRow row = new TableRow(this);
        GradientDrawable gd = createCellBackground(Color.parseColor("#E8F5E9"));

        row.addView(createTextView(String.valueOf(listId), gd));
        row.addView(createTextView(String.valueOf(id), gd));
        row.addView(createTextView(name, gd));

        tableLayout.addView(row);
    }

    // Create headers for the table
    private void createTableHeaders() {
        TableRow headerRow = new TableRow(this);
        GradientDrawable headerBackground = createCellBackground(Color.parseColor("#81C784"));

        headerRow.addView(createTextView(getString(R.string.itemId), headerBackground));
        headerRow.addView(createTextView(getString(R.string.id), headerBackground));
        headerRow.addView(createTextView(getString(R.string.name), headerBackground));

        tableLayout.addView(headerRow);
    }

    // Create a TextView with specified text and background
    private TextView createTextView(String text, GradientDrawable background) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(30);
        textView.setBackground(background);
        return textView;
    }

    // Create a GradientDrawable for cell backgrounds
    private GradientDrawable createCellBackground(int color) {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(color);
        gd.setStroke(3, Color.BLACK);
        return gd;
    }

    // Extract the first integer value found in a string
    private int extractIntegerValue(String str) {
        for (String part : str.split(" ")) {
            try {
                return Integer.parseInt(part);
            } catch (NumberFormatException ignored) {
            }
        }
        return 0;  // Return 0 if no integer is found
    }
}
