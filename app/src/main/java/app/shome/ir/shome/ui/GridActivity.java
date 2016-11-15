package app.shome.ir.shome.ui;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

import app.shome.ir.shome.R;

public class GridActivity extends AppCompatActivity {

    private static final int NBR_ITEMS = 9;
    int[] span = new int[]{1, 2, 3, 1, 1, 1, 2, 3, 1, 1, 1};
    DeviceView[] deviceViews = new DeviceView[NBR_ITEMS];
    private GridLayout mGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        mGrid = (GridLayout) findViewById(R.id.grid_layout);

        final LayoutInflater inflater = LayoutInflater.from(this);
//        Random random = new Random();
//        for (int i = 0; i < NBR_ITEMS; i++) {
//            span[i] = random.nextInt(3) + 1;
//        }


        // face view takes 2 rows, 1 column -- zero index based
//        GridLayout.Spec faceRow = GridLayout.spec(0,2,1f); // starts row 0, takes 2 rows
//        GridLayout.Spec faceCol = GridLayout.spec(0,1,1f); // starts col 0, takes 1 col
//
//        GridLayout.Spec titleRow = GridLayout.spec(0); // starts row 0, takes 1 row
//        GridLayout.Spec titleCol = GridLayout.spec(1, 3,1f); // starts col 1, takes 3 cols
//
//        GridLayout.Spec plusRow = GridLayout.spec(1); // starts row 1, takes 1 row
//        GridLayout.Spec plusCol = GridLayout.spec(1,1,1f); // starts col 1, takes 1 col
//
//        GridLayout.Spec minusRow = GridLayout.spec(1); // starts row 1, takes 1 row
//        GridLayout.Spec minusCol = GridLayout.spec(2,1,1f); // starts col 1, takes 1 col
//
//        GridLayout.Spec checkRow = GridLayout.spec(1); // starts row 1, takes 1 row
//        GridLayout.Spec checkCol = GridLayout.spec(3,1,1f); // starts col 1, takes 1 col
//
//// create the LayoutParams using our row/col for each view
//        GridLayout.LayoutParams faceParams = new GridLayout.LayoutParams(faceRow, faceCol);
////        faceParams.setGravity(Gravity.FILL_VERTICAL); // fill vertical so we take up the full 2 rows
//
//        TextView faceText = new TextView(this);
//        faceText.setPadding(32, 32, 32, 32); // add some random padding to make the views bigger
//        faceText.setLayoutParams(faceParams);
//        faceText.setText("FACE");
//        faceText.setGravity(Gravity.CENTER);
//        faceText.setBackgroundColor(Color.RED);
//        mGrid.addView(faceText, faceParams);
//
//        GridLayout.LayoutParams titleParams = new GridLayout.LayoutParams(titleRow, titleCol);
////        titleParams.setGravity(Gravity.FILL_HORIZONTAL); // fill horizontal so we take up the full 3 columns
//        TextView titleText = new TextView(this);
//        titleText.setPadding(32, 32, 32, 32);
//        titleText.setLayoutParams(titleParams);
//        titleText.setText("TITLE");
//        titleText.setGravity(Gravity.CENTER);
//        titleText.setBackgroundColor(Color.BLUE);
//        mGrid.addView(titleText, titleParams);
//
//        GridLayout.LayoutParams minusParams = new GridLayout.LayoutParams(minusRow, minusCol);
//        TextView minusText = new TextView(this);
////        minusParams.setGravity(Gravity.FILL_HORIZONTAL);
//        minusText.setPadding(32, 32, 32, 32);
//        minusText.setLayoutParams(minusParams);
//        minusText.setText("MIN");
//        minusText.setGravity(Gravity.CENTER);
//        minusText.setBackgroundColor(Color.YELLOW);
//        mGrid.addView(minusText, minusParams);
//
//        GridLayout.LayoutParams plusParams = new GridLayout.LayoutParams(plusRow, plusCol);
//        TextView plusText = new TextView(this);
////        plusParams.setGravity(Gravity.FILL_HORIZONTAL);
//        plusText.setPadding(32, 32, 32, 32);
//        plusText.setLayoutParams(plusParams);
//        plusText.setText("PLS");
//        plusText.setGravity(Gravity.CENTER);
//        plusText.setBackgroundColor(Color.GREEN);
//        mGrid.addView(plusText, plusParams);
//
//        GridLayout.LayoutParams checkParams = new GridLayout.LayoutParams(checkRow, checkCol);
//        TextView checkText = new TextView(this);
////        checkParams.setGravity(Gravity.FILL_HORIZONTAL);
//        checkText.setPadding(32, 32, 32, 32);
//        checkText.setLayoutParams(checkParams);
//        checkText.setText("CHK");
//        checkText.setGravity(Gravity.CENTER);
//        checkText.setBackgroundColor(Color.MAGENTA);
//        mGrid.addView(checkText, checkParams);
        int row = 0, column = 0;
        for (int i = 0; i < NBR_ITEMS; i++) {
            final View itemView = inflater.inflate(R.layout.grid_item, mGrid, false);
            final TextView text = (TextView) itemView.findViewById(R.id.text);
            text.setText(String.valueOf(i + 1));
            if (column + span[i] > 3) {
                row++;
                column = 0;
            }
//            System.out.println("" + i + "," + row + "," + column + ":" + span[i]);

            GridLayout.Spec rowSpec = GridLayout.spec(row);
            GridLayout.Spec columnSpec = GridLayout.spec(column, span[i], 1f);
            GridLayout.LayoutParams doubleLayoutParams = new GridLayout.LayoutParams(rowSpec, columnSpec);
            mGrid.addView(itemView, doubleLayoutParams);
            column += span[i];
            deviceViews[i] = new DeviceView();
            deviceViews[i].index = i;
            deviceViews[i].span = span[i];
            deviceViews[i].numtext = i + 1;
            deviceViews[i].view = itemView;
            itemView.setOnLongClickListener(new LongPressListener());
            itemView.setTag(deviceViews[i]);
        }
        mGrid.setOnDragListener(new DragListener());

    }

    class LongPressListener implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View view) {
            final ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDrag(data, shadowBuilder, view, 0);
            view.setVisibility(View.INVISIBLE);
            return true;
        }
    }

    class DragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            final View view = (View) event.getLocalState();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_LOCATION:
                    if (view == v) return true;
                    mGrid.removeView(view);
                    reArrange(view, event.getX(), event.getY());
                    break;
                case DragEvent.ACTION_DROP:
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    if (!event.getResult()) {
                        view.setVisibility(View.VISIBLE);
                    }
                    break;
            }
            return true;
        }
    }


    private void reArrange(View v, float x, float y) {
        final float cellWidth = mGrid.getWidth() / mGrid.getColumnCount();
        final int column1 = (int) (x / cellWidth);
        final float cellHeight = mGrid.getHeight() / mGrid.getRowCount();
        final int row1 = (int) Math.floor(y / cellHeight);
        Arrays.sort(deviceViews);
        int row = 0, column = 0;
        DeviceView a = (DeviceView) v.getTag();
        for (int i = 0; i < NBR_ITEMS; i++) {
            if (deviceViews[i].view == v)
                continue;
            if (row == row1) {
                if (column1 <= column || column1 < column + deviceViews[i].span || column + a.span + deviceViews[i].span > mGrid.getColumnCount()) {
                    for (int j = a.index; j < i; j++) {

                        deviceViews[j].index = j;
                    }
                    for (int j = i; j < NBR_ITEMS; j++) {

                        deviceViews[j].index = j + 1;
                    }
                    a.index = i;
                    break;
                }
            } else if (row > row1) {
                for (int j = a.index; j < i; j++) {

                    deviceViews[j].index = j;
                }
                for (int j = i; j < NBR_ITEMS; j++) {

                    deviceViews[j].index = j + 1;
                }
                a.index = i;
                break;
            }

            if (column + deviceViews[i].span > 3) {
                row++;
                column = 0;
            }
            column += deviceViews[i].span;
        }
        Arrays.sort(deviceViews);

        mGrid.removeAllViews();
        row = 0;
        column = 0;
        for (int i = 0; i < NBR_ITEMS; i++) {

            if (column + deviceViews[i].span > 3) {
                row++;
                column = 0;
            }

            GridLayout.Spec rowSpec = GridLayout.spec(row);
            GridLayout.Spec columnSpec = GridLayout.spec(column, deviceViews[i].span, 1f);
            GridLayout.LayoutParams doubleLayoutParams = new GridLayout.LayoutParams(rowSpec, columnSpec);
            mGrid.addView(deviceViews[i].view, doubleLayoutParams);
            column += deviceViews[i].span;
        }

//        }

        return;
    }

    private int calculateNewIndex(float x, float y) {
        // calculate which column to move to
        final float cellWidth = mGrid.getWidth() / mGrid.getColumnCount();
        final int column = (int) (x / cellWidth);

        // calculate which row to move to
        final float cellHeight = mGrid.getHeight() / mGrid.getRowCount();
        final int row = (int) Math.floor(y / cellHeight);

        // the items in the GridLayout is organized as a wrapping list
        // and not as an actual grid, so this is how to get the new index
        int index = row * mGrid.getColumnCount() + column;
        if (index >= mGrid.getChildCount()) {
            index = mGrid.getChildCount() - 1;
        }

        return index;
    }


    class DeviceView implements Comparable<DeviceView> {
        int span;
        Integer index;
        View view;
        int numtext;

        @Override
        public int compareTo(DeviceView another) {
            return index.compareTo(another.index);
        }

        @Override
        public String toString() {
            return "" + index;
        }
    }
}
