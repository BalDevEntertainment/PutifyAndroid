package com.baldev.putify.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import com.baldev.putify.R;
import com.baldev.putify.helpers.VolleyHelper;
import com.baldev.putify.presenters.MessagePresenter;
import com.baldev.putify.presenters.SendMessagePresenter;
import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendMessageActivity extends AppCompatActivity implements SendMessageView {

	@BindView(R.id.edit_text_message) EditText messageEditText;
	private MessagePresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_message);
		ButterKnife.bind(this);

		presenter = new SendMessagePresenter();
		String refreshedToken = FirebaseInstanceId.getInstance().getToken();
		Log.i("FCMTOKEN", refreshedToken != null ? refreshedToken : "Missing");

	}

	@Override
	@OnClick(R.id.button_send_message)
	public void onSendPressed() {
		presenter.sendMessage(messageEditText.getText());
		VolleyHelper.sendTestNotification(this);
	}
}
