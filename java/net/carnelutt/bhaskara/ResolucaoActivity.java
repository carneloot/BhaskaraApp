package net.carnelutt.bhaskara;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ResolucaoActivity extends ActionBarActivity
{
    private Toolbar mToolbar;
    private TextView lblResolucao;
    private TextView lblDelta;

    float a, b, c, delta;
    String strResolucao;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resolucao);

        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lblResolucao = (TextView) findViewById(R.id.lblResolucao);
        lblDelta = (TextView) findViewById(R.id.lblDelta);

        a = getIntent().getFloatExtra("a", 0);
        b = getIntent().getFloatExtra("b", 0);
        c = getIntent().getFloatExtra("c", 0);
        delta = getIntent().getFloatExtra("delta", 0);

        defResolucao();
        lblResolucao.setText(Html.fromHtml(strResolucao));
    }

    private void defResolucao()
    {
        if (delta >= 0)
        {
            lblDelta.setText(R.string.deltaPositivo);
            strResolucao = String.format(
                    "&#916; = (%.2f)&sup2; - 4 x (%.2f) x (%.2f)<br>" +
                            "&#916; = %.2f %s<br>" +
                            "&#916; = %.2f<br><br>" +
                            "" +
                            "X = <sup> - (%.2f) &plusmn; &radic;%.2f</sup> / <sub>2 x (%.2f)</sub><br>" +
                            "X = <sup>%.2f &plusmn; %.2f</sup> / <sub>%.2f</sub><br><br>" +
                            "" +
                            "X&#8321; = <sup>%.2f + %.2f</sup> / <sub>%.2f</sub><br>" +
                            "X&#8321; = %.2f<br><br>" +
                            "" +
                            "X&#8322; = <sup>%.2f - %.2f</sup> / <sub>%.2f</sub><br>" +
                            "X&#8322; = %.2f",
                    b, a, c,
                    b * b, (-4 * a * c > 0) ? ("+" + -4 * a * c).replace('.',',') : ("" + -4 * a * c).replace('.',','),
                    delta,
                    b, delta,
                    a,
                    -b, Math.sqrt(delta),
                    2 * a,
                    -b, Math.sqrt(delta),
                    2 * a,
                    (-b + Math.sqrt(delta)) / (2 * a),
                    -b, Math.sqrt(delta),
                    2 * a,
                    (-b - Math.sqrt(delta)) / (2 * a)
            );
        } else
        {
            lblDelta.setText(R.string.deltaNegativo);
            strResolucao = String.format(
                    "&#916; = (%.2f)&sup2; - 4 x (%.2f) x (%.2f)<br>" +
                            "&#916; = %.2f %s<br>" +
                            "&#916; = %.2f" +
                            "<br><br>" +
                            "X = <sup> - (%.2f) &plusmn; &radic;- (%.2f)</sup> / <sub>2 x (%.2f)</sub><br>" +
                            "X = <sup>%.2f</sup>/<sub>%.2f</sub> &plusmn; <sup>%.2fi</sup>/<sub>%.2f</sub>" +
                            "<br><br>" +
                            "X&#8321; = <sup>%.2f</sup>/<sub>%.2f</sub> + <sup>%.2fi</sup>/<sub>%.2f</sub><br>" +
                            "X&#8321; = %.2f + %.2fi" +
                            "<br><br>" +
                            "X&#8322; = <sup>%.2f</sup>/<sub>%.2f</sub> - <sup>%.2fi</sup>/<sub>%.2f</sub><br>" +
                            "X&#8322; = %.2f - %.2fi",
                    b, a, c,
                    b * b, (-4 * a * c > 0) ? ("+" + -4 * a * c).replace('.',',') : ("" + -4 * a * c).replace('.',','),
                    delta,

                    b, delta, a,
                    -b, 2*a, Math.sqrt(-delta), 2*a,

                    -b, 2*a, Math.sqrt(-delta), 2*a,
                    -b / (2*a), Math.sqrt(-delta)/(2*a),

                    -b, 2*a, Math.sqrt(-delta), 2*a,
                    -b / (2*a), Math.sqrt(-delta)/(2*a)
            );
        }
    }
}
