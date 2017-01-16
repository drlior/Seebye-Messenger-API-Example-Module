package com.example.examplemodul;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.seebye.messengerapi.api.MessengerAPI;
import com.seebye.messengerapi.api.constants.Action;
import com.seebye.messengerapi.api.constants.MessageType;
import com.seebye.messengerapi.api.constants.Messenger;
import com.seebye.messengerapi.api.constants.ResponseType;

import java.util.Timer;
import java.util.TimerTask;

public class WhatsAppListener extends Service
        implements receiver.ResponseCallback{
    private static final int RESULT_SELECT_IMAGE		= 1000;
    private static final int RESULT_SELECT_AUDIO		= 1001;
    private static final int RESULT_SELECT_VIDEO		= 1002;

    private static final int RESPONSE_ACTION_ACCESS			= 1000;
    private static final int RESPONSE_ACTION_CONTACTS		= 1001;
    private static final int RESPONSE_ACTION_SENDMESSAGE	= 1002;
    private static final int RESPONSE_ACTION_SENDMEDIA		= 1003;
    private static final int RESPONSE_ACTION_LOADMESSAGES	= 1004;
    private static final int RESPONSE_ACTION_COUNTMESSAGES	= 1005;

    private String m_strIDMessenger = null;
    private TimerTask t;

    private static Timer timer = new Timer();
    private Context ctx;

    public WhatsAppListener() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }



    public void onCreate()
    {
        super.onCreate();
        ctx = this;
        t = new mainTask();
        startService();
    }

    private void startService()
    {
        timer.scheduleAtFixedRate(t, 0, 5000);
    }

    @Override
    public void onResponseReceived(long lBroadcastID, int nRequestActionID, @NonNull ResponseType responseType, @NonNull Action action, @NonNull Bundle extras) {

    }

    private class mainTask extends TimerTask
    {
        public void run()
        {
            //Toast.makeText(ctx, "test", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendMedia(MessageType messageType, String strFileLocation)
    {
        if(m_strIDMessenger == null)
        {
            Toast.makeText(this, R.string.err_select, Toast.LENGTH_SHORT).show();
        }
        else
        {
            try
            {
                receiver.registerRequest(
                        this
                        , MessengerAPI.sendMessage(Messenger.WHATSAPP, m_strIDMessenger, messageType, strFileLocation)
                                .addRequestActionID(RESPONSE_ACTION_SENDMEDIA)
                                .send()
                                .getID()
                        , this);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

    }

    public void onDestroy()
    {
        t.cancel();
        super.onDestroy();
        Toast.makeText(this, "Service Stopped ...", Toast.LENGTH_SHORT).show();
    }
}
