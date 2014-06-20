package com.telerik.examples.examples.chart.series.pie;

import android.content.res.Resources;

import com.telerik.examples.R;
import com.telerik.examples.common.DataClass;
import com.telerik.examples.viewmodels.ExampleDataProvider;
import com.telerik.widget.chart.engine.dataPoints.DataPoint;
import com.telerik.widget.chart.engine.databinding.FieldNameDataPointBinding;
import com.telerik.widget.chart.visualization.common.ChartSeries;
import com.telerik.widget.chart.visualization.pieChart.PieSeries;
import com.telerik.widget.chart.visualization.pieChart.PieSeriesLabelRenderer;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class PieSeriesFragment extends PieFragment {
    class CustomLabelRenderer extends PieSeriesLabelRenderer {
        CustomLabelRenderer(ChartSeries owner) {
            super(owner);
        }

        @Override
        protected String getLabelText(DataPoint dataPoint) {
            String defaultLabel = super.getLabelText(dataPoint);
            DataClass dataItem = (DataClass) dataPoint.getDataItem();
            return dataItem.category + " " + defaultLabel;
        }
    }

    public PieSeriesFragment() {
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_pie_series;
    }

    @Override
    protected void preparePieChart() {
        final PieSeries pieSeries = new PieSeries(context);

        Resources res = this.getResources();
        pieSeries.setSliceOffset(res.getDimension(R.dimen.twodp));
        pieSeries.setShowLabels(true);
        pieSeries.setValueBinding(new FieldNameDataPointBinding("value"));

        pieSeries.setData(ExampleDataProvider.pieData());
        CustomLabelRenderer renderer = new CustomLabelRenderer(pieSeries);
        pieSeries.setLabelRenderer(renderer);

        pieChart.getSeries().add(pieSeries);
    }
}
