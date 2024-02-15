package com.example.home_widget_demo

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.View
import android.widget.RemoteViews
import es.antonborri.home_widget.HomeWidgetProvider
import android.content.Intent
import android.app.PendingIntent

class CustomHomeView : HomeWidgetProvider() {
    override fun onUpdate(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetIds: IntArray,
            widgetData: SharedPreferences
    ) {
        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.widget_layout).apply {
                val imageName = widgetData.getString("filename", null)
                setImageViewBitmap(R.id.widget_image, BitmapFactory.decodeFile(imageName))

                val intent = Intent(context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

                setOnClickPendingIntent(R.id.widget_image, pendingIntent)
            }
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }


}