package com.example.news.presentation.newsnavigation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(modifier: Modifier = Modifier, query: String, onQueryChange: (value: String)->Unit) {
    val focusManager = LocalFocusManager.current
    BasicTextField(
        modifier = modifier
            .padding(8.dp),
        value = TextFieldValue(text = query, selection = TextRange(query.length)),
        singleLine = true,
        textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground),
        onValueChange = { onQueryChange(it.text)},
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
            this.defaultKeyboardAction(ImeAction.Done)
        }),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground)
    ) { innerTextField ->
        Row(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.primaryContainer
                )
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "search")
            innerTextField()
        }
    }
}