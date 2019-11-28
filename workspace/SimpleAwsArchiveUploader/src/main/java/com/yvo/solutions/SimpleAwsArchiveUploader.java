package com.yvo.solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.glacier.AmazonGlacier;
import com.amazonaws.services.glacier.AmazonGlacierClientBuilder;
import com.amazonaws.services.glacier.transfer.ArchiveTransferManager;
import com.amazonaws.services.glacier.transfer.ArchiveTransferManagerBuilder;
import com.amazonaws.services.glacier.transfer.UploadResult; 

public final class SimpleAwsArchiveUploader {
	public final static String vaultName = "HighVaultageRock";
    public final static String archiveToUpload = "file.txt";
    public final static String archiveName = "file";
    public final static Regions region = Regions.EU_WEST_3;
    
    private static void generateFile() throws IOException {
    	File file = new File(archiveToUpload);
    	if (!file.exists()) {
    		if (!file.createNewFile()) {
    			throw new IOException("Failed to create the file");
    		}
    		
    		FileWriter writer = new FileWriter(file);
        	writer.write("Content of the file");
        	writer.close();
    	}
    }
    
    private static AmazonGlacier getAwsGlacier() {
    	ProfileCredentialsProvider credentials = new ProfileCredentialsProvider();
    	
    	return AmazonGlacierClientBuilder.standard()
    			.withRegion(region)
    			.withCredentials(credentials)
    			.build();
    }
    
    private static ArchiveTransferManager getArchiveTransferManager(AmazonGlacier client) {
    	ArchiveTransferManagerBuilder builder = new ArchiveTransferManagerBuilder();
    	
    	return builder.withGlacierClient(client).build();
    }
    
    private static UploadResult sendFileToAwsGlacier() throws AmazonServiceException, AmazonClientException, FileNotFoundException {
    	AmazonGlacier client = getAwsGlacier();
    	ArchiveTransferManager atm = getArchiveTransferManager(client);
    	
    	return atm.upload(vaultName, archiveName, new File(archiveToUpload));
    }
    
    public static void main(String[] args) {
    	try {
	    	generateFile();
	    	UploadResult result = sendFileToAwsGlacier();
	    	System.out.println("Archive ID: " + result.getArchiveId());
    	}
    	catch (Exception e) {
            System.err.println(e);
        }
    }
}