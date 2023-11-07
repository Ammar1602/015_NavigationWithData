@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.esque.ui.theme

import android.text.Layout.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.esque.R
import java.lang.reflect.Modifier

@Composable
fun HalamanSatu(
    pilihanRasa: List<String>,
    onSelectionChanged: (String) -> Unit,
    onConfirmationButtonClicked: (Int) -> Unit,
    onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier()
)
{
    var rasaYgDipilih by rememberSaveable { mutableStateOf("") }
    var textJmlBeli by rememberSaveable { mutableStateOf("") }

    Column (modifier = Modifier,
        verticalArrangement = Arrangement.SpaceBetween){
        Column (modifier = Modifier,
            verticalArrangement = Arrangement.SpaceBetween) {
            Column (modifier =
            Modifier.padding(dimensionResource(id = R.dimen.padding_medium))) {
                    pilihanRasa.forEach{ item ->
                        Row(modifier = Modifier.selectable(selected = rasaYgDipilih == item,onClick = {
                            rasaYgDipilih = item
                            onSelectionChanged(item)
                            }
                        ),
                            verticalAlignment = Alignment.CenterVertically){
                            RadioButton(selected = rasaYgDipilih == item,
                                onClick = {
                                    rasaYgDipilih = item
                                    onSelectionChanged(item)}
                            )
                            Text(item)
                        }
                    }
                Divider(
                    thickness = dimensionResource(id = R.dimen.thicknes_divider),
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_medium))
                )
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_medium))
                    .weight(1f, false),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),){
                    OutlinedTextField(
                        value = textJmlBeli,
                        singleLine = true,
                        shape = MaterialTheme.shapes.large,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.width(150.dp)
                        label = { Text(text = "Jumlah Order")},
                        onValueChange = {
                            textJmlBeli = it
                        }
                    )
                    Button(
                        modifier =  Modifier.weight(1f),
                        enabled = textJmlBeli.isNotEmpty(),
                        onClick = { onConfirmationButtonClicked(textJmlBeli.toInt())}
                    ) {
                        Text(stringResource(id = R.string.confirm))
                    }
                }
                Divider(
                    thickness = dimensionResource(id = R.dimen.thicknes_divider),
                    modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_medium))
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                        .weight(1f, false),
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
                    verticalAlignment = Alignment.Bottom
                ){
                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        enabled = textJmlBeli.isNotEmpty(),
                        onClick = onNextButtonClicked
                    ) {
                        Text(stringResource(id = R.string.next))
                    }
                }
            }
        }

    }
}