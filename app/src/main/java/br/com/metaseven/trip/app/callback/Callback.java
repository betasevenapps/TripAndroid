package br.com.metaseven.trip.app.callback;

import java.util.Objects;

/**
 * Created by vagnnermartins on 25/08/14.
 */
public interface Callback {
    public void onReturn(Exception error, Object... objects);
}