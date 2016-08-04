package com.baldev.putify.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.baldev.putify.R;
import com.baldev.putify.presenter.MessagePresenter;
import com.baldev.putify.presenter.SendMessagePresenter;

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
	}

	@Override
	@OnClick(R.id.button_send_message)
	public void onSendPressed() {
		presenter.sendMessage(messageEditText.getText());
	}
}
