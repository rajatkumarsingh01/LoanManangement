package com.task.loanapplication.presentation.auth


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.task.loanapplication.domain.MainViewModel
import java.io.InputStream


@Composable
fun UploadDocumentsScreen(
    viewModel: MainViewModel,
    onUploadComplete: () -> Unit,

) {
    val localContext= LocalContext.current

    var aadharUri by remember { mutableStateOf<Uri?>(null) }
    var panUri by remember { mutableStateOf<Uri?>(null) }
    var bankingStatementUri by remember { mutableStateOf<Uri?>(null) }
    var licenseUri by remember { mutableStateOf<Uri?>(null) }
    var electricBillUri by remember { mutableStateOf<Uri?>(null) }

    Scaffold(
        bottomBar = {
            Row(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                // Upload Button
                Button(
                    onClick = {
                        viewModel.uploadDocument("AadharCard", aadharUri)
                        viewModel.uploadDocument("PANCard", panUri)
                        viewModel.uploadDocument("BankingStatement", bankingStatementUri)
                        viewModel.uploadDocument("License", licenseUri)
                        viewModel.uploadDocument("ElectricBill", electricBillUri)
                        onUploadComplete()
                        showToast(localContext , "Documents uploaded successfully")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Upload Documents")
                }

                if (viewModel.uploadProgress.value) {
                    CircularProgressIndicator()
                }
            }
        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    top = it.calculateTopPadding(),
                    bottom = it.calculateBottomPadding(),
                    start = 16.dp,
                    end = 16.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(18.dp))
            // Info Text
            Text(
                text = "Upload the following documents:",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Aadhar Card Upload
            DocumentUploadView(
                documentName = "Aadhar Card:",
                documentUri = aadharUri,
                onDocumentSelected = { uri -> aadharUri = uri }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // PAN Card Upload
            DocumentUploadView(
                documentName = "PAN Card:",
                documentUri = panUri,
                onDocumentSelected = { uri -> panUri = uri }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Banking Statement Upload
            DocumentUploadView(
                documentName = "Banking Statement(One-Year):",
                documentUri = bankingStatementUri,
                onDocumentSelected = { uri -> bankingStatementUri = uri }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // License Upload
            DocumentUploadView(
                documentName = "Driving License:",
                documentUri = licenseUri,
                onDocumentSelected = { uri -> licenseUri = uri }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Electric Bill Upload
            DocumentUploadView(
                documentName = "Electric Bill:",
                documentUri = electricBillUri,
                onDocumentSelected = { uri -> electricBillUri = uri }
            )

            Spacer(modifier = Modifier.height(16.dp))

            val onRent by viewModel.isLivingOnRent.collectAsState()

            // House Tax Receipt or Rental Agreement
            DocumentUploadView(
                documentName = if (onRent) "Rental Agreement" else "House Tax Receipt:",
                documentUri = electricBillUri,
                onDocumentSelected = { uri -> electricBillUri = uri }
            )

            Spacer(modifier = Modifier.height(48.dp))
        }
    }
}

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
//loading uploaded image using bitmap
fun loadBitmapFromUri(context: Context, uri: Uri): Bitmap? {
    var inputStream: InputStream? = null
    return try {
        inputStream = context.contentResolver.openInputStream(uri)
        BitmapFactory.decodeStream(inputStream)
    } catch (e: Exception) {
        null
    } finally {
        inputStream?.close()
    }

}



@Composable
fun DocumentUploadView(
    documentName: String,
    documentUri: Uri?,
    onDocumentSelected: (Uri) -> Unit
) {
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let { selectedUri ->
                onDocumentSelected(selectedUri)
            }
        }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        // Document Name
        Text(text = documentName, style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(8.dp))

        // Upload Button
        OutlinedButton(
            onClick = { launcher.launch("image/*") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = if (documentUri != null) "Change Document" else "Upload Document")
        }

        // Selected Document Preview
        if (documentUri != null) {

            documentUri.let { uri ->
                val context = LocalContext.current
                val bitmap = loadBitmapFromUri(context, uri)
                bitmap?.let {
                    val painter = remember { BitmapPainter(bitmap.asImageBitmap()) }
                    Image(
                        painter = painter,
                        contentDescription = "Document Preview",
                        modifier = Modifier
                            .size(100.dp)
                            .padding(top = 8.dp)
                    )
                }
            }
        }
    }

}

