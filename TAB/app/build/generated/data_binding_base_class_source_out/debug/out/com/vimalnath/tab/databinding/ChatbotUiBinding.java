// Generated by view binder compiler. Do not edit!
package com.vimalnath.tab.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.vimalnath.tab.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ChatbotUiBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageButton backBtn;

  @NonNull
  public final RecyclerView chatView;

  @NonNull
  public final EditText enterMsg;

  @NonNull
  public final ImageButton sendBtn;

  @NonNull
  public final ConstraintLayout textBox;

  private ChatbotUiBinding(@NonNull ConstraintLayout rootView, @NonNull ImageButton backBtn,
      @NonNull RecyclerView chatView, @NonNull EditText enterMsg, @NonNull ImageButton sendBtn,
      @NonNull ConstraintLayout textBox) {
    this.rootView = rootView;
    this.backBtn = backBtn;
    this.chatView = chatView;
    this.enterMsg = enterMsg;
    this.sendBtn = sendBtn;
    this.textBox = textBox;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ChatbotUiBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ChatbotUiBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.chatbot_ui, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ChatbotUiBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.backBtn;
      ImageButton backBtn = ViewBindings.findChildViewById(rootView, id);
      if (backBtn == null) {
        break missingId;
      }

      id = R.id.chatView;
      RecyclerView chatView = ViewBindings.findChildViewById(rootView, id);
      if (chatView == null) {
        break missingId;
      }

      id = R.id.enterMsg;
      EditText enterMsg = ViewBindings.findChildViewById(rootView, id);
      if (enterMsg == null) {
        break missingId;
      }

      id = R.id.sendBtn;
      ImageButton sendBtn = ViewBindings.findChildViewById(rootView, id);
      if (sendBtn == null) {
        break missingId;
      }

      id = R.id.textBox;
      ConstraintLayout textBox = ViewBindings.findChildViewById(rootView, id);
      if (textBox == null) {
        break missingId;
      }

      return new ChatbotUiBinding((ConstraintLayout) rootView, backBtn, chatView, enterMsg, sendBtn,
          textBox);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
